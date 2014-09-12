package com.sarality.app.datastore.field;

import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldContainer;
import com.sarality.app.data.field.FieldContainerFactory;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.CursorDataExtractor;
import com.sarality.app.datastore.FieldColumnMapping;
import com.sarality.app.datastore.FieldColumnMappingList;
import com.sarality.app.datastore.column.ColumnProcessor;
import com.sarality.app.datastore.query.Query;

/**
 * Generic implementation of the CursorDataExtractor that uses Field and FieldContainers to extract data from a Cursor.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> Type of data object that is extracted.
 */
public class FieldBasedCursorDataExtractor<T> implements CursorDataExtractor<T> {

  private final FieldContainerFactory<T> factory;
  private final FieldColumnMappingList mappingList;

  public FieldBasedCursorDataExtractor(FieldColumnMappingList mappingList, FieldContainerFactory<T> factory) {
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
  public T extract(Cursor cursor, Query query) {
    FieldContainer<T> fieldValues = newFieldContainer();
    FieldColumnMappingList mappings = getFieldColumnMappings();
    for (FieldColumnMapping mapping : mappings.get()) {
      Field field = mapping.getField();
      Column column = mapping.getColumn();
      ColumnProcessor<?> extractor = mapping.getColumnProcessor();
      fieldValues.getFieldValue(field).castFrom(extractor.extract(cursor, column));
    }
    return fieldValues.createBuilder().build();
  }
}
