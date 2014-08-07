package com.sarality.app.datastore.column.test;

import hirondelle.date4j.DateTime;

import java.util.Date;
import java.util.TimeZone;

import junit.framework.TestCase;
import android.content.ContentValues;
import android.database.MatrixCursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.DateColumnProcessor;
import com.sarality.app.datastore.column.DateTimeColumnProcessor;

/**
 * Tests for {@link DateColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DateTimeColumnProcessorTest extends TestCase {
  private DateTimeColumnProcessor processor;
  private MatrixCursor cursor;
  private Column column;

  public DateTimeColumnProcessorTest(String name) {
    super(name);
  }

  @Override
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new DateTimeColumnProcessor(Field.DataType.DATETIME);
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, null, false));
    assertNotNull(processor);
  }

  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", "2013-12-25 13:11:45" });
    cursor.moveToNext();
    DateTime value = processor.extract(cursor, column);
    assertNotNull(value);
    DateTime dateValue = new DateTime(2013, 12, 25, 13, 11, 45, 0);
    assertEquals(dateValue, value);
  }

  public void testExtract_DateOnlyColumn() {
    processor = new DateTimeColumnProcessor(Field.DataType.DATE_ONLY);
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, null, false));

    cursor.addRow(new Object[] { "Row 1", "2013-12-25" });
    cursor.moveToNext();
    DateTime value = processor.extract(cursor, column);
    assertNotNull(value);
    DateTime dateValue = DateTime.forDateOnly(2013, 12, 25);
    assertEquals(dateValue, value);
  }

  public void testExtract_TimeOnlyColumn() {
    processor = new DateTimeColumnProcessor(Field.DataType.TIME_ONLY);
    cursor.addRow(new Object[] { "Row 1", "13:11:45" });
    cursor.moveToNext();
    DateTime value = processor.extract(cursor, column);
    assertNotNull(value);
    DateTime dateValue = DateTime.forTimeOnly(13, 11, 45, 0);
    assertEquals(dateValue, value);
  }

  public void testExtract_DateAsNumberColumnFormat() {
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.DATE_AS_INT, false));

    cursor.addRow(new Object[] { "Row 1", "20131225131145" });
    cursor.moveToNext();
    DateTime value = processor.extract(cursor, column);
    assertNotNull(value);
    DateTime dateValue = new DateTime(2013, 12, 25, 13, 11, 45, 0);
    assertEquals(dateValue, value);
  }

  public void testExtract_EpochColumnFormat() {
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));

    DateTime dateTime = new DateTime(2012, 12, 25, 13, 11, 45, 0);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));

    cursor.addRow(new Object[] { "Row 1", String.valueOf(dateValue.getTime()) });
    cursor.moveToNext();
    DateTime value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(dateTime, value);
  }

  public void testExtract_EpochDateOnlyColumn() {
    processor = new DateTimeColumnProcessor(Field.DataType.DATE_ONLY);
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));

    DateTime dateTime = DateTime.forDateOnly(2012, 12, 25);
    Date dateValue = new Date(dateTime.getMilliseconds(TimeZone.getDefault()));

    cursor.addRow(new Object[] { "Row 1", String.valueOf(dateValue.getTime()) });
    cursor.moveToNext();
    DateTime value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(dateTime, value);
  }

  public void testExtract_InvalidColumnName() {
    cursor.addRow(new Object[] { "Row 1", "2013-12-25 13:11:45" });
    cursor.moveToNext();
    column = new TestColumn("Column3", new ColumnSpec(ColumnDataType.DATETIME, null, false));
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
    DateTime value = processor.extract(cursor, column);
    assertNull(value);
  }

  public void testExtract_InvalidValue() {
    cursor.addRow(new Object[] { "Row 1", "Invalid" });
    cursor.moveToNext();
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if data stored in Long column is not a valid Long value");
    } catch (IllegalStateException e) {
      assertEquals("Cannot extract DateTime value from Column Column2. Cannot convert value stored in "
          + "column to a DateTime : Invalid", e.getMessage());
    }
  }

  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    DateTime dateValue = new DateTime(2014, 12, 25, 13, 11, 45, 0);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("2014-12-25 13:11:45", contentValues.get(column.getName()));
  }

  public void testPopulate_DateOnly() {
    processor = new DateTimeColumnProcessor(Field.DataType.DATE_ONLY);
    ContentValues contentValues = new ContentValues();
    DateTime dateValue = DateTime.forDateOnly(2014, 12, 25);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("2014-12-25", contentValues.get(column.getName()));
  }

  public void testPopulate_TimeOnly() {
    processor = new DateTimeColumnProcessor(Field.DataType.TIME_ONLY);
    ContentValues contentValues = new ContentValues();
    DateTime dateValue = DateTime.forTimeOnly(14, 22, 35, 0);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("14:22:35", contentValues.get(column.getName()));
  }

  public void testPopulate_EpochColumnFormat() {
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));

    ContentValues contentValues = new ContentValues();
    DateTime dateValue = new DateTime(2014, 12, 25, 13, 11, 45, 0);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(dateValue.getMilliseconds(TimeZone.getDefault()), contentValues.get(column.getName()));
  }

  public void testPopulate_EpochDateOnlyColumnFormat() {
    processor = new DateTimeColumnProcessor(Field.DataType.DATE_ONLY);
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));

    ContentValues contentValues = new ContentValues();
    DateTime dateValue = DateTime.forDateOnly(2014, 12, 25);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(dateValue.getMilliseconds(TimeZone.getDefault()), contentValues.get(column.getName()));
  }

  public void testPopulate_EpochTimeOnlyColumnFormat() {
    processor = new DateTimeColumnProcessor(Field.DataType.TIME_ONLY);
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));

    ContentValues contentValues = new ContentValues();
    DateTime dateValue = DateTime.forTimeOnly(11, 20, 35, 0);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));

    Long excpectedValue = Long.valueOf((11 * 3600000) + (20 * 60000) + 35000);
    assertEquals(excpectedValue, contentValues.get(column.getName()));
  }

  public void testPopulate_DateAsIntColumnFormat() {
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.DATE_AS_INT, false));

    ContentValues contentValues = new ContentValues();
    DateTime dateValue = new DateTime(2014, 12, 25, 13, 11, 45, 0);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(20141225131145L, contentValues.get(column.getName()));
  }

  public void testPopulate_DateAsIntDateOnlyColumnFormat() {
    processor = new DateTimeColumnProcessor(Field.DataType.DATE_ONLY);
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.DATE_AS_INT, false));

    ContentValues contentValues = new ContentValues();
    DateTime dateValue = DateTime.forDateOnly(2014, 12, 25);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(20141225L, contentValues.get(column.getName()));
  }

  public void testPopulate_DateAsIntTimeOnlyColumnFormat() {
    processor = new DateTimeColumnProcessor(Field.DataType.TIME_ONLY);
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.DATE_AS_INT, false));

    ContentValues contentValues = new ContentValues();
    DateTime dateValue = DateTime.forTimeOnly(14, 12, 25, 0);
    processor.populate(contentValues, column, dateValue);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(141225L, contentValues.get(column.getName()));
  }

  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (DateTime) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));

    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DATETIME, ColumnFormat.EPOCH, false));
    processor.populate(contentValues, column, (DateTime) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    DateTime dateValue = new DateTime(2014, 12, 25, 13, 11, 45, 0);
    FieldValue<?> value = fieldValues.createFieldValue(TestField.DATE_TIME_FIELD, dateValue);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("2014-12-25 13:11:45", contentValues.get(column.getName()));
  }

  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    TestFieldValues fieldValues = new TestFieldValues();
    FieldValue<?> value = fieldValues.createFieldValue(TestField.DATE_TIME_FIELD, null);

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
      assertEquals("Cannot convert data for type STRING to a DateTime while adding value for column Column2",
          e.getMessage());
    }
  }
}
