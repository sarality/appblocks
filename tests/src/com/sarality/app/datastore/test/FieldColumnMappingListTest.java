package com.sarality.app.datastore.test;

import java.util.List;

import junit.framework.TestCase;
import android.test.MoreAsserts;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.test.TestField;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnMapping;
import com.sarality.app.datastore.FieldColumnMappingList;
import com.sarality.app.datastore.column.ColumnProcessor;
import com.sarality.app.datastore.column.StringColumnProcessor;

/**
 * Tests for {@link FieldColumnMappingList}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class FieldColumnMappingListTest extends TestCase {

  public void testFieldColumnMappings() {
    Field field = new TestField("Field1", Field.DataType.STRING);
    Column column = new TestColumn("Column1", null);
    ColumnProcessor<String> processor = new StringColumnProcessor();
    FieldColumnMappingList mappings = new FieldColumnMappingList();
    mappings.addEntry(field, column, processor);

    List<FieldColumnMapping> mappingList = mappings.get();
    assertEquals(1, mappingList.size());
    assertEquals(new FieldColumnMapping(field, column, processor), mappingList.get(0));
  }

  public void testFieldColumnMappings_EmptyMappings() {
    FieldColumnMappingList mappings = new FieldColumnMappingList();
    List<FieldColumnMapping> mappingList = mappings.get();
    assertEquals(0, mappingList.size());
  }

  public void testFieldColumnMappings_MultipleMappings() {
    Field field = new TestField("Field1", Field.DataType.STRING);
    Column column = new TestColumn("Column1", null);
    Field field2 = new TestField("Field2", Field.DataType.STRING);
    Column column2 = new TestColumn("Column2", null);
    ColumnProcessor<String> processor = new StringColumnProcessor();
    FieldColumnMappingList mappings = new FieldColumnMappingList();
    mappings.addEntry(field, column, processor);
    mappings.addEntry(field2, column2, processor);

    List<FieldColumnMapping> mappingList = mappings.get();
    assertEquals(2, mappingList.size());
    MoreAsserts.assertContentsInAnyOrder(mappingList, new FieldColumnMapping(field, column, processor),
        new FieldColumnMapping(field2, column2, processor));
  }
}
