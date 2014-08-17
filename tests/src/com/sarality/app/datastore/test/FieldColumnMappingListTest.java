package com.sarality.app.datastore.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnMapping;
import com.sarality.app.datastore.FieldColumnMappingList;
import com.sarality.app.datastore.column.ColumnProcessor;
import com.sarality.app.datastore.column.StringColumnProcessor;
import com.sarality.app.datastore.column.test.TestField;
import com.sarality.app.datastore.db.test.TestColumn;
import com.sarality.app.view.action.test.MoreAsserts;

/**
 * Tests for {@link FieldColumnMappingList}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
@RunWith(RobolectricTestRunner.class)
public class FieldColumnMappingListTest extends TestCase {

  @Test
  public void testFieldColumnMappings() {
    Column column = new TestColumn("Column1", null);
    ColumnProcessor<String> processor = new StringColumnProcessor();
    FieldColumnMappingList mappings = new FieldColumnMappingList();
    mappings.addEntry(TestField.STRING_FIELD, column, processor);

    List<FieldColumnMapping> mappingList = mappings.get();
    assertEquals(1, mappingList.size());
    assertEquals(new FieldColumnMapping(TestField.STRING_FIELD, column, processor), mappingList.get(0));
  }

  @Test
  public void testFieldColumnMappings_EmptyMappings() {
    FieldColumnMappingList mappings = new FieldColumnMappingList();
    List<FieldColumnMapping> mappingList = mappings.get();
    assertEquals(0, mappingList.size());
  }

  @Test
  public void testFieldColumnMappings_MultipleMappings() {
    Column column = new TestColumn("Column1", null);
    Column column2 = new TestColumn("Column2", null);
    ColumnProcessor<String> processor = new StringColumnProcessor();
    FieldColumnMappingList mappings = new FieldColumnMappingList();
    mappings.addEntry(TestField.STRING_FIELD, column, processor);
    mappings.addEntry(TestField.STRING_FIELD, column2, processor);

    List<FieldColumnMapping> mappingList = mappings.get();
    assertEquals(2, mappingList.size());
    MoreAsserts.assertContentsInAnyOrder(mappingList, new FieldColumnMapping(TestField.STRING_FIELD, column, processor),
        new FieldColumnMapping(TestField.STRING_FIELD, column2, processor));
  }
}
