package com.sarality.app.datastore.column.test;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.content.ContentValues;
import android.database.MatrixCursor;

import com.sarality.app.data.field.FieldValue;
import com.sarality.app.data.field.GenericFieldValueFactory;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.column.LongColumnProcessor;
import com.sarality.app.datastore.db.test.TestColumn;

/**
 * Tests for {@link LongColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
@RunWith(RobolectricTestRunner.class)
public class LongColumnProcessorTest extends TestCase {
  private LongColumnProcessor processor;
  private MatrixCursor cursor;
  private Column column;
  private GenericFieldValueFactory factory;

  @Before
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new LongColumnProcessor();
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.INTEGER, null, false));
    factory = new GenericFieldValueFactory();
    assertNotNull(processor);
  }

  @Test
  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", Long.valueOf(1) });
    cursor.moveToNext();
    Long value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(Long.valueOf(1), value);
  }

  @Test
  public void testExtract_InvalidColumnName() {
    cursor.addRow(new Object[] { "Row 1", Long.valueOf(1) });
    cursor.moveToNext();
    column = new TestColumn("Column3", new ColumnSpec(ColumnDataType.INTEGER, null, false));
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if Column with given name does not exist");
    } catch (IllegalArgumentException e) {
      assertEquals("Column with name Column3 not found.", e.getMessage());
    }
  }

  @Test
  public void testExtract_NullValue() {
    cursor.addRow(new Object[] { "Row 1", null });
    cursor.moveToNext();
    Long value = processor.extract(cursor, column);
    assertNull(value);
  }

  @Test
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

  @Test
  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, 100L);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(100L, contentValues.get(column.getName()));
  }

  @Test
  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (Long) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  @Test
  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Long> value = factory.longValue(TestField.LONG_FIELD);
    value.setValue(200L);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(200L, contentValues.get(column.getName()));
  }

  @Test
  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Long> value = factory.longValue(TestField.LONG_FIELD);
    value.setValue(null);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));

    processor.populate(contentValues, column, (FieldValue<?>) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  @Test
  public void testPopulate_InvalidFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Date> value = factory.dateValue(TestField.DATE_FIELD);
    value.setValue(new Date());

    try {
      processor.populate(contentValues, column, value);
      fail("Passing in a String field value to a Long column should throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot convert data for type DATE to Long while adding value for column Column2", e.getMessage());
    }
  }
}
