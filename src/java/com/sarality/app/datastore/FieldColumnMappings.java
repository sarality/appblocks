package com.sarality.app.datastore;

import java.util.List;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.data.field.Field;
import com.sarality.app.datastore.column.ColumnProcessor;

/**
 * Base implementation of a class that provides a List of Mappings between a Field and a datastore Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class FieldColumnMappings {

  // List of Configuration that define a mapping between a DataObject field and a Database Column
  private final List<FieldColumnMapping> mappingList = Lists.of();

  /**
   * Add a FieldColumnMapping entry.
   * <p>
   * This should ideally be called from the constructor of the implementing class.
   * 
   * @param field The field for the DataObject that this is a mapping for.
   * @param column The database Column that the field is a mapped to.
   * @param extractor The class used to extract data from the cursor when reading the data store.
   * @param populator The class used to populate ContentValues when updating or creating a row in the data store.
   */
  public void addEntry(Field field, Column column, ColumnProcessor<?> processor) {
    mappingList.add(new FieldColumnMapping(field, column, processor));
  }

  /**
   * @return List of FieldColumnMapping entries.
   */
  public List<FieldColumnMapping> get() {
    return mappingList;
  }
}
