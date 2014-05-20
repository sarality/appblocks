package com.sarality.app.datastore;

import java.util.ArrayList;
import java.util.List;

import com.dothat.app.bills.BillsTable.Column;
import com.sarality.app.data.Field;
import com.sarality.app.datastore.extractor.ColumnValueExtractor;
import com.sarality.app.datastore.populator.FieldDataValuePopulator;

/**
 * Base implementation of a class that provides a List of Configuration that define a 
 * mapping between a DataObject field and a Database Column.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class BaseFieldColumnConfigProvider {
  // List of Configuration that define a mapping between a DataObject field and a Database Column
  private final List<FieldColumnConfig> configList = new ArrayList<FieldColumnConfig>();

  /**
   * Add a FieldColumnConfig entry to the provider. This should ideally be called from the constructor
   * of the implementing class.
   * 
   * @param field The field for the DataObject that this is a config for.
   * @param column The database Column that this is a config for.
   * @param extractor The class used to extract data from the cursor when reading the database
   * @param populator The class used to populate ContentValues when updating or creating a row in the database
   */
  protected void addEntry(Field field, Column column, ColumnValueExtractor<?> extractor, FieldDataValuePopulator populator) {
    configList.add(new FieldColumnConfig(field, column, extractor, populator));        
  }

  /**
   * @return List of FieldColumnConfig entries.
   */
  public List<FieldColumnConfig> provide() {
    return configList;
  }
}
