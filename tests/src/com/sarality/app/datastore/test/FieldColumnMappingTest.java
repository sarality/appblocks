package com.sarality.app.datastore.test;

import junit.framework.TestCase;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.test.TestField;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnMapping;
import com.sarality.app.datastore.column.ColumnProcessor;
import com.sarality.app.datastore.column.StringColumnProcessor;

/**
 * Tests for {@link FieldColumnMapping}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class FieldColumnMappingTest extends TestCase {

  public void testFieldColumnMapping() {
    Field field = new TestField("Field1", Field.DataType.STRING);
    Column column = new TestColumn("Column1", null);
    ColumnProcessor<String> processor = new StringColumnProcessor();
    FieldColumnMapping mapping = new FieldColumnMapping(field, column, processor);
    assertSame(field, mapping.getField());
    assertSame(column, mapping.getColumn());
    assertSame(processor, mapping.getColumnProcessor());
  }

  public void testFieldColumnMapping_NullField() {
    Column column = new TestColumn("Column1", null);
    ColumnProcessor<String> processor = new StringColumnProcessor();
    try {
      new FieldColumnMapping(null, column, processor);
      fail("FieldColumnMapping should throw an exception when created with null field");
    } catch (IllegalArgumentException e) {
      assertEquals("Cannot define mapping when the field is null", e.getMessage());
    }
  }

  public void testFieldColumnMapping_NullColumn() {
    Field field = new TestField("Field1", Field.DataType.STRING);
    ColumnProcessor<String> processor = new StringColumnProcessor();
    try {
      new FieldColumnMapping(field, null, processor);
      fail("FieldColumnMapping should throw an exception when created with null column");
    } catch (IllegalArgumentException e) {
      assertEquals("Column mapped to field Field1 cannot be null", e.getMessage());
    }
  }

  public void testFieldColumnMapping_NullColumnProcessor() {
    Field field = new TestField("Field1", Field.DataType.STRING);
    Column column = new TestColumn("Column1", null);
    try {
      new FieldColumnMapping(field, column, null);
      fail("FieldColumnMapping should throw an exception when created with null column processor");
    } catch (IllegalArgumentException e) {
      assertEquals("Must define a processor for field Field1 and column Column1", e.getMessage());
    }
  }
}
