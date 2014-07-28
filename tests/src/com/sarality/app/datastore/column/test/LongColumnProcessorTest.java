package com.sarality.app.datastore.column.test;

import java.util.Date;

import junit.framework.TestCase;
import android.content.ContentValues;
import android.database.MatrixCursor;

import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.LongColumnProcessor;

/**
 * Tests for {@link LongColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class LongColumnProcessorTest extends TestCase {
  private LongColumnProcessor processor;
  private MatrixCursor cursor;
  private Column column;

  public LongColumnProcessorTest(String name) {
    super(name);
  }

  @Override
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new LongColumnProcessor();
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.INTEGER, null, false));
    assertNotNull(processor);
  }

  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", Long.valueOf(1) });
    cursor.moveToNext();
    Long value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(Long.valueOf(1), value);
  }

  public void testExtract_NullValue() {
    cursor.addRow(new Object[] { "Row 1", null });
    cursor.moveToNext();
    Long value = processor.extract(cursor, column);
    assertNull(value);
  }

  public void testExtract_InvalidValue() {
    cursor.addRow(new Object[] { "Row 1", "Invalid" });
    cursor.moveToNext();
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if data stored in Long column is not a valid Long value");
    } catch (IllegalStateException e) {
      assertEquals("Cannot extract Long value from Column Column2. Cannot convert value stored in "
          + "column to a Long : Invalid", e.getMessage());
    }
  }

  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, 100L);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(100L, contentValues.get(column.getName()));
  }

  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (Long) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    FieldValue<?> value = fieldValues.createFieldValue(TestField.LONG_FIELD, 200L);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(200L, contentValues.get(column.getName()));
  }

  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    FieldValue<?> value = fieldValues.createFieldValue(TestField.LONG_FIELD, null);

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
      fail("Passing in a String field value to a Long column should throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot convert data for type DATE to Long while adding value for column Column2", e.getMessage());
    }
  }
}
