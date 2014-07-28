package com.sarality.app.datastore.column.test;

import java.util.Date;

import junit.framework.TestCase;
import android.content.ContentValues;
import android.database.MatrixCursor;

import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.IntegerColumnProcessor;

/**
 * Tests for {@link IntegerColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class IntegerColumnProcessorTest extends TestCase {
  private IntegerColumnProcessor processor;
  private MatrixCursor cursor;
  private Column column;

  public IntegerColumnProcessorTest(String name) {
    super(name);
  }

  @Override
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new IntegerColumnProcessor();
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.INTEGER, null, false));
    assertNotNull(processor);
  }

  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", Integer.valueOf(1) });
    cursor.moveToNext();
    Integer value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(Integer.valueOf(1), value);
  }

  public void testExtract_NullValue() {
    cursor.addRow(new Object[] { "Row 1", null });
    cursor.moveToNext();
    Integer value = processor.extract(cursor, column);
    assertNull(value);
  }

  public void testExtract_InvalidValue() {
    cursor.addRow(new Object[] { "Row 1", "Invalid" });
    cursor.moveToNext();
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if data stored in Integer column is not a valid Integer value");
    } catch (IllegalStateException e) {
      assertEquals("Cannot extract Integer value from Column Column2. Cannot convert value stored in "
          + "column to a Integer : Invalid", e.getMessage());
    }
  }

  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, 100);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(100, contentValues.get(column.getName()));
  }

  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (Integer) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    FieldValue<?> value = fieldValues.createFieldValue(TestField.INTEGER_FIELD, 200);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(200, contentValues.get(column.getName()));
  }

  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    FieldValue<?> value = fieldValues.createFieldValue(TestField.INTEGER_FIELD, null);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));

    processor.populate(contentValues, column, (FieldValue<?>) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_InvalidFieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    FieldValue<?> value = fieldValues.createFieldValue(TestField.DATE_FIELD, new Date());

    try {
      processor.populate(contentValues, column, value);
      fail("Passing in a String field value to a Integer column should throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot convert data for type DATE to Integer while adding value for column Column2",
          e.getMessage());
    }
  }
}