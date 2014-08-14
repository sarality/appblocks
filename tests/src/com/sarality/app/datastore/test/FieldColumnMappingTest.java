package com.sarality.app.datastore.test;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnMapping;
import com.sarality.app.datastore.column.ColumnProcessor;
import com.sarality.app.datastore.column.StringColumnProcessor;
import com.sarality.app.datastore.column.test.TestField;
import com.sarality.app.datastore.db.test.TestColumn;

/**
 * Tests for {@link FieldColumnMapping}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
@RunWith(RobolectricTestRunner.class)
public class FieldColumnMappingTest extends TestCase {

  @Test
  public void testFieldColumnMapping() {
    Column column = new TestColumn("Column1", null);
    ColumnProcessor<String> processor = new StringColumnProcessor();
    FieldColumnMapping mapping = new FieldColumnMapping(TestField.STRING_FIELD, column, processor);
    assertSame(TestField.STRING_FIELD, mapping.getField());
    assertSame(column, mapping.getColumn());
    assertSame(processor, mapping.getColumnProcessor());
  }

  @Test
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

  @Test
  public void testFieldColumnMapping_NullColumn() {
    ColumnProcessor<String> processor = new StringColumnProcessor();
    try {
      new FieldColumnMapping(TestField.STRING_FIELD, null, processor);
      fail("FieldColumnMapping should throw an exception when created with null column");
    } catch (IllegalArgumentException e) {
      assertEquals("Column mapped to field STRING_FIELD cannot be null", e.getMessage());
    }
  }

  @Test
  public void testFieldColumnMapping_NullColumnProcessor() {
    Column column = new TestColumn("Column1", null);
    try {
      new FieldColumnMapping(TestField.STRING_FIELD, column, null);
      fail("FieldColumnMapping should throw an exception when created with null column processor");
    } catch (IllegalArgumentException e) {
      assertEquals("Must define a processor for field STRING_FIELD and column Column1", e.getMessage());
    }
  }
}
