package com.sarality.app.datastore.column.test;

import junit.framework.TestCase;
import android.content.ContentValues;
import android.database.MatrixCursor;

import com.sarality.app.data.field.FieldValue;
import com.sarality.app.data.field.GenericFieldValueFactory;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.StringColumnProcessor;

/**
 * Tests for {@link StringColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class StringColumnProcessorTest extends TestCase {
  private StringColumnProcessor processor;
  private MatrixCursor cursor;
  private Column column;
  private GenericFieldValueFactory factory;

  public StringColumnProcessorTest(String name) {
    super(name);
  }

  @Override
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new StringColumnProcessor();
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.TEXT, null, false));
    factory = new GenericFieldValueFactory();
    assertNotNull(processor);
  }

  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", "Value 1" });
    cursor.moveToNext();
    String value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals("Value 1", value);
  }

  public void testExtract_InvalidColumnName() {
    cursor.addRow(new Object[] { "Row 1", "Value 1" });
    cursor.moveToNext();
    column = new TestColumn("Column3", new ColumnSpec(ColumnDataType.TEXT, null, false));
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if Column with given name does not exist");
    } catch (IllegalArgumentException e) {
      assertEquals("Column with name Column3 not found.", e.getMessage());
    }
  }

  public void testExtract_NullValue() {
    cursor.addRow(new Object[] { "Row 2", null });
    cursor.moveToNext();
    String value = processor.extract(cursor, column);
    assertNull(value);
  }

  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, "Value 1");
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("Value 1", contentValues.get(column.getName()));
  }

  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (String) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<String> value = factory.stringValue(TestField.STRING_FIELD);
    value.setValue("Value 1");

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("Value 1", contentValues.get(column.getName()));
  }

  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<String> value = factory.stringValue(TestField.STRING_FIELD);
    value.setValue(null);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));

    processor.populate(contentValues, column, (FieldValue<?>) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }
}
