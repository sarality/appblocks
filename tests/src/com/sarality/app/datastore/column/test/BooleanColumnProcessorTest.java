package com.sarality.app.datastore.column.test;

import junit.framework.TestCase;
import android.content.ContentValues;
import android.database.MatrixCursor;

import com.sarality.app.data.field.FieldValue;
import com.sarality.app.data.field.GenericFieldValueFactory;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.BooleanColumnProcessor;

/**
 * Tests for {@link BooleanColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class BooleanColumnProcessorTest extends TestCase {
  private BooleanColumnProcessor processor;
  private MatrixCursor cursor;
  private Column column;
  private GenericFieldValueFactory factory;

  public BooleanColumnProcessorTest(String name) {
    super(name);
  }

  @Override
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new BooleanColumnProcessor();
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.BOOLEAN, null, false));
    factory = new GenericFieldValueFactory();
    assertNotNull(processor);
  }

  public void testExtract_TrueValue() {
    cursor.addRow(new Object[] { "Row 1", 1 });
    cursor.moveToNext();
    Boolean value = processor.extract(cursor, column);
    assertNotNull(value);
    assertTrue(value);
  }

  public void testExtract_FalseValue() {
    cursor.addRow(new Object[] { "Row 1", 0 });
    cursor.moveToNext();
    Boolean value = processor.extract(cursor, column);
    assertNotNull(value);
    assertFalse(value);
  }

  public void testExtract_InvalidColumnName() {
    cursor.addRow(new Object[] { "Row 1", 0 });
    cursor.moveToNext();
    column = new TestColumn("Column3", new ColumnSpec(ColumnDataType.BOOLEAN, null, false));
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if Column with given name does not exist");
    } catch (IllegalArgumentException e) {
      assertEquals("Column with name Column3 not found.", e.getMessage());
    }
  }

  public void testExtract_NullValue() {
    cursor.addRow(new Object[] { "Row 1", null });
    cursor.moveToNext();
    Boolean value = processor.extract(cursor, column);
    assertNull(value);
  }

  public void testExtract_InvalidIntegerValue() {
    cursor.addRow(new Object[] { "Row 1", 3 });
    cursor.moveToNext();
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if data stored in Boolean column is not 0 or 1");
    } catch (IllegalStateException e) {
      assertEquals("Cannot extract Boolean value from Column Column2. Cannot convert value stored in "
          + "column to a Boolean : 3", e.getMessage());
    }
  }

  public void testExtract_InvalidValue() {
    cursor.addRow(new Object[] { "Row 1", "Invalid" });
    cursor.moveToNext();
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if data stored in Boolean column is not 0 or 1");
    } catch (IllegalStateException e) {
      assertEquals("Cannot extract Boolean value from Column Column2. Cannot convert value stored in "
          + "column to a Boolean : Invalid", e.getMessage());
    }
  }

  public void testPopulate_TrueValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, Boolean.TRUE);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(Boolean.TRUE, contentValues.get(column.getName()));
  }

  public void testPopulate_FalseValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, Boolean.FALSE);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(Boolean.FALSE, contentValues.get(column.getName()));
  }

  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (Boolean) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_TrueFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Boolean> value = factory.booleanValue(TestField.BOOLEAN_FIELD);
    value.setValue(Boolean.TRUE);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(Boolean.TRUE, contentValues.get(column.getName()));
  }

  public void testPopulate_FalseFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Boolean> value = factory.booleanValue(TestField.BOOLEAN_FIELD);
    value.setValue(Boolean.FALSE);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(Boolean.FALSE, contentValues.get(column.getName()));
  }

  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Boolean> value = factory.booleanValue(TestField.BOOLEAN_FIELD);
    value.setValue(null);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));

    processor.populate(contentValues, column, (FieldValue<?>) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_InvalidFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Long> value = factory.longValue(TestField.LONG_FIELD);
    value.setValue(100L);

    try {
      processor.populate(contentValues, column, value);
      fail("Passing in a Long field value to a Boolean column should throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot convert data for type LONG to Boolean while adding value for column Column2", e.getMessage());
    }
  }
}
