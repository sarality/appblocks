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
import com.sarality.app.datastore.column.EnumColumnProcessor;
import com.sarality.app.datastore.db.test.TestColumn;

/**
 * Tests for {@link EnumColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
@RunWith(RobolectricTestRunner.class)
public class EnumColumnProcessorTest extends TestCase {
  private EnumColumnProcessor<TestEnum> processor;
  private MatrixCursor cursor;
  private Column column;
  private GenericFieldValueFactory factory;

  @Before
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new EnumColumnProcessor<TestEnum>(TestEnum.class);
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.ENUM, null, false));
    factory = new GenericFieldValueFactory();
    assertNotNull(processor);
  }

  @Test
  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", TestEnum.VALUE_1.name() });
    cursor.moveToNext();
    TestEnum value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(TestEnum.VALUE_1, value);
  }

  @Test
  public void testExtract_InvalidColumnName() {
    cursor.addRow(new Object[] { "Row 1", TestEnum.VALUE_1.name() });
    cursor.moveToNext();
    column = new TestColumn("Column3", new ColumnSpec(ColumnDataType.ENUM, null, false));
    try {
      processor.extract(cursor, column);
      fail("Extract should throw exception if Column with given name does not exist");
    } catch (IllegalArgumentException e) {
      assertEquals("Column with name Column3 not found.", e.getMessage());
    }
  }

  @Test
  public void testExtract_NullValue() {
    cursor.addRow(new Object[] { "Row 2", null });
    cursor.moveToNext();
    TestEnum value = processor.extract(cursor, column);
    assertNull(value);
  }

  @Test
  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, TestEnum.VALUE_1);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(TestEnum.VALUE_1.name(), contentValues.get(column.getName()));
  }

  @Test
  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (TestEnum) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  @Test
  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<TestEnum> value = factory.enumValue(TestField.ENUM_FIELD, TestEnum.class);
    value.setValue(TestEnum.VALUE_1);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals("VALUE_1", contentValues.get(column.getName()));
  }

  @Test
  public void testPopulate_NullFieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<TestEnum> value = factory.enumValue(TestField.ENUM_FIELD, TestEnum.class);
    value.setValue(null);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));

    processor.populate(contentValues, column, (FieldValue<?>) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  private static enum TestEnum {
    VALUE_1,
    VALUE_2
  }
}
