package com.sarality.app.datastore.field;

import android.content.ContentValues;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldContainer;
import com.sarality.app.data.field.FieldContainerFactory;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnMapping;
import com.sarality.app.datastore.FieldColumnMappingList;
import com.sarality.app.datastore.column.ColumnProcessor;
import com.sarality.app.datastore.populator.ContentValuesPopulator;

/**
 * Generic implementation of the ContentValuesPopulator that uses Field and FieldContainers to populate a ContentValues
 * instance.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> Type of data object that is the source of the data used to populate the ContentValues instance.
 */
public class FieldBasedContentValuesPopulator<T> implements ContentValuesPopulator<T> {

  private final FieldContainerFactory<T> factory;
  private final FieldColumnMappingList mappingList;

  public FieldBasedContentValuesPopulator(FieldColumnMappingList mappingList, FieldContainerFactory<T> factory) {
    this.mappingList = mappingList;
    this.factory = factory;
  }

  /**
   * @return Mappings between Field and their corresponding Columns.
   */
  public FieldColumnMappingList getFieldColumnMappings() {
    return mappingList;
  }

  /**
   * @return New empty instance of FieldContainer for the source data object.
   */
  public FieldContainer<T> newFieldContainer() {
    return factory.newContainer();
  }

  @Override
  public boolean populate(ContentValues contentValues, T data) {
    FieldContainer<T> container = newFieldContainer();
    FieldColumnMappingList mappings = getFieldColumnMappings();
    for (FieldColumnMapping mapping : mappings.get()) {
      Field field = mapping.getField();
      Column column = mapping.getColumn();
      ColumnProcessor<?> populator = mapping.getColumnProcessor();
      populator.populate(contentValues, column, container.getFieldValue(field));
    }
    return true;
  }
}
