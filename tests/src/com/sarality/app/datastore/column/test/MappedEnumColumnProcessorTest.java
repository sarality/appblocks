package com.sarality.app.datastore.column.test;

import junit.framework.TestCase;
import android.content.ContentValues;
import android.database.MatrixCursor;

import com.sarality.app.data.field.FieldValue;
import com.sarality.app.data.field.GenericFieldValueFactory;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.MappedEnum;
import com.sarality.app.datastore.column.EnumColumnProcessor;
import com.sarality.app.datastore.column.MappedEnumColumnProcessor;

/**
 * Tests for {@link EnumColumnProcessor}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class MappedEnumColumnProcessorTest extends TestCase {
  private MappedEnumColumnProcessor<Integer, TestEnum> processor;
  private MatrixCursor cursor;
  private Column column;
  private GenericFieldValueFactory factory;

  public MappedEnumColumnProcessorTest(String name) {
    super(name);
  }

  @Override
  public void setUp() {
    cursor = new MatrixCursor(new String[] { "Column1", "Column2" });
    processor = new MappedEnumColumnProcessor<Integer, TestEnum>(TestEnum.class, TestEnum.values());
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.INTEGER, null, false));
    factory = new GenericFieldValueFactory();
    assertNotNull(processor);
  }

  public void testExtract() {
    cursor.addRow(new Object[] { "Row 1", 1 });
    cursor.moveToNext();
    TestEnum value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(TestEnum.VALUE_1, value);
  }

  public void testExtract_TextColumn() {
    cursor.addRow(new Object[] { "Row 1", "2" });
    cursor.moveToNext();

    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.TEXT, null, false));
    TestEnum value = processor.extract(cursor, column);
    assertNotNull(value);
    assertEquals(TestEnum.VALUE_2, value);
  }
  
  public void testExtract_InvalidColumnName() {
    cursor.addRow(new Object[] { "Row 1", TestEnum.VALUE_1.name() });
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
    TestEnum value = processor.extract(cursor, column);
    assertNull(value);
  }

  public void testPopulate() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, TestEnum.VALUE_2);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(TestEnum.VALUE_2.getMappedValue(), contentValues.get(column.getName()));
  }

  public void testPopulate_IntegerColumn() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, TestEnum.VALUE_2);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(2, contentValues.get(column.getName()));
  }

  public void testPopulate_DoubleColumn() {
    ContentValues contentValues = new ContentValues();
    column = new TestColumn("Column2", new ColumnSpec(ColumnDataType.DOUBLE, null, false));
    try {
      processor.populate(contentValues, column, TestEnum.VALUE_2);
      fail("Populate should throw exception if Column data type is not INTEGER or TEXT");
    } catch (IllegalStateException e) {
      assertEquals("Mapped Enum are only supported for INTEGER or TEXT columns. Cannot populate Enum value VALUE_2 "
          + "into column of type DOUBLE", e.getMessage());
    }
  }

  public void testPopulate_NullValue() {
    ContentValues contentValues = new ContentValues();
    processor.populate(contentValues, column, (TestEnum) null);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNull(contentValues.get(column.getName()));
  }

  public void testPopulate_FieldValue() {
    ContentValues contentValues = new ContentValues();
    FieldValue<TestEnum> value = factory.enumValue(TestField.ENUM_FIELD, TestEnum.class);
    value.setValue(TestEnum.VALUE_1);

    processor.populate(contentValues, column, value);
    assertTrue(contentValues.containsKey(column.getName()));
    assertNotNull(contentValues.get(column.getName()));
    assertEquals(TestEnum.VALUE_1.getMappedValue(), contentValues.get(column.getName()));
  }

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

  private static enum TestEnum implements MappedEnum<Integer> {
    VALUE_1(1),
    VALUE_2(2);

    private Integer mappedValue;
    
    private TestEnum(Integer value) {
      this.mappedValue = value;
    }

    @Override
    public Integer getMappedValue() {
      return mappedValue;
    }
  }
}
