package com.sarality.app.datastore.column.test;

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
import com.sarality.app.datastore.column.DoubleColumnProcessor;
import com.sarality.app.datastore.db.test.TestColumn;

/**
 * Tests for {@link DoubleColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
@RunWith(RobolectricTestRunner.class)
public class DoubleColumnProcessorTest extends TestCase {
  private DoubleColumnProcessor processor;
  private MatrixCursor cursor;
  private Column column;
  private GenericFieldValueFactory factory;

  @Before
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new DoubleColumnProcessor();
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DOUBLE, null, false));
    factory = new GenericFieldValueFactory();
    assertNotNull(processor);
  }

  @Test
  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", 1D });
    cursor.moveToNext();
    Double value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(1D, value);
  }

  @Test
  public void testExtract_InvalidColumnName() {
    cursor.addRow(new Object[] { "Row 1", 1000D });
    cursor.moveToNext();
    column = new TestColumn("Column3", new ColumnSpec(ColumnDataType.DOUBLE, null, false));
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
    Double value = processor.extract(cursor, column);
    assertNull(value);
  }

  @Test
  public void testExtract_InvalidValue() {
    cursor.addRow(new Object[] { "Row 1", "Invalid" });
    cursor.moveToNext();
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if data stored in Double column is not 0 or 1");
    } catch (IllegalStateException e) {
      assertEquals("Cannot extract Double value from Column Column2. Cannot convert value stored in "
          + "column to a Double : Invalid", e.getMessage());
    }
  }

  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, 100D);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(100D, contentValues.get(column.getName()));
  }

  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (Double) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Double> value = factory.doubleValue(TestField.DOUBLE_FIELD);
    value.setValue(200D);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(200D, contentValues.get(column.getName()));
  }

  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<Double> value = factory.doubleValue(TestField.DOUBLE_FIELD);
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
    FieldValue<String> value = factory.stringValue(TestField.STRING_FIELD);
    value.setValue("Invalid");

    try {
      processor.populate(contentValues, column, value);
      fail("Passing in a Long field value to a Double column should throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot convert data for type STRING to Double while adding value for column Column2",
          e.getMessage());
    }
  }
}
