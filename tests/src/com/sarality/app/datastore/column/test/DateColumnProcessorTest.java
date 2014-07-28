package com.sarality.app.datastore.column.test;

import hirondelle.date4j.DateTime;

import java.util.Date;
import java.util.TimeZone;

import junit.framework.TestCase;
import android.content.ContentValues;
import android.database.MatrixCursor;

import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.DateColumnProcessor;

/**
 * Tests for {@link DateColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DateColumnProcessorTest extends TestCase {
  private DateColumnProcessor processor;
  private MatrixCursor cursor;
  private Column column;

  public DateColumnProcessorTest(String name) {
    super(name);
  }

  @Override
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new DateColumnProcessor();
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, null, false));
    assertNotNull(processor);
  }

  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", "2013-12-25 13:11:45" });
    cursor.moveToNext();
    Date value = processor.extract(cursor, column);
    assertNotNull(value);
    DateTime dateTime = new DateTime(2013, 12, 25, 13, 11, 45, 0);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));
    assertEquals(dateValue, value);
  }

  public void testExtract_DateAsNumberColumnFormat() {
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.DATE_AS_INT, false));

    cursor.addRow(new Object[] { "Row 1", "20131225131145" });
    cursor.moveToNext();
    Date value = processor.extract(cursor, column);
    assertNotNull(value);
    DateTime dateTime = new DateTime(2013, 12, 25, 13, 11, 45, 0);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));
    assertEquals(dateValue, value);
  }

  public void testExtract_EpochColumnFormat() {
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));

    DateTime dateTime = new DateTime(2012, 12, 25, 13, 11, 45, 0);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));
    
    cursor.addRow(new Object[] { "Row 1", String.valueOf(dateValue.getTime()) });
    cursor.moveToNext();
    Date value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(dateValue, value);
  }
  
  public void testExtract_NullValue() {
    cursor.addRow(new Object[] { "Row 1", null });
    cursor.moveToNext();
    Date value = processor.extract(cursor, column);
    assertNull(value);
  }

  public void testExtract_InvalidValue() {
    cursor.addRow(new Object[] { "Row 1", "Invalid" });
    cursor.moveToNext();
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if data stored in Long column is not a valid Long value");
    } catch (IllegalStateException e) {
      assertEquals("Cannot extract Date value from Column Column2. Cannot convert value stored in "
          + "column to a Date : Invalid", e.getMessage());
    }
  }

  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    DateTime dateTime = new DateTime(2014, 12, 25, 13, 11, 45, 0);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("2014-12-25 13:11:45", contentValues.get(column.getName()));
  }

  public void testPopulate_EpochColumnFormat() {
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));
    
    ContentValues contentValues = new ContentValues();
    DateTime dateTime = new DateTime(2014, 12, 25, 13, 11, 45, 0);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(dateValue.getTime(), contentValues.get(column.getName()));
  }

  public void testPopulate_DateAsIntColumnFormat() {
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.DATE_AS_INT, false));
    
    ContentValues contentValues = new ContentValues();
    DateTime dateTime = new DateTime(2014, 12, 25, 13, 11, 45, 0);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(20141225131145L, contentValues.get(column.getName()));
  }
  
  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (Date) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));

    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));
    processor.populate(contentValues, column, (Date) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));    
  }

  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    DateTime dateTime = new DateTime(2014, 12, 25, 13, 11, 45, 0);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));
    FieldValue<?> value = fieldValues.createFieldValue(TestField.DATE_FIELD, dateValue);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("2014-12-25 13:11:45", contentValues.get(column.getName()));
  }

  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    FieldValue<?> value = fieldValues.createFieldValue(TestField.DATE_FIELD, null);

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
    FieldValue<?> value = fieldValues.createFieldValue(TestField.STRING_FIELD, new Date());

    try {
      processor.populate(contentValues, column, value);
      fail("Passing in a String field value to a Date column should throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot convert data for type STRING to a Date while adding value for column Column2", 
          e.getMessage());
    }
  }
}
