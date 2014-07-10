package com.sarality.app.datastore.extractor;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.database.Cursor;

import com.sarality.app.data.FieldBasedDataObject;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnConfig;
import com.sarality.app.datastore.query.Query;

/**
 * Generic implementation of a {@link CursorDataExtractor} based on Metadata that defines a Mapping between a Field and
 * Column and information on how to extract data from the column and populate a field of the given DataObject.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The Type of DataObject that is populated from the Cursor.
 */
public abstract class GenericCursorDataExtractor<T extends FieldBasedDataObject<T>> implements CursorDataExtractor<T> {

  // Map between a Column Name and the
  private Map<String, FieldColumnConfig> columnNameConfigMap = new HashMap<String, FieldColumnConfig>();

  /**
   * Constructor.
   * 
   * @param mappingList List of Mapping between Field and Column and how to extract data from the column and populate a
   *          field of the given DataObject.
   */
  public GenericCursorDataExtractor(List<FieldColumnConfig> mappingList) {
    for (FieldColumnConfig mapping : mappingList) {
      String columnName = mapping.getColumn().getName().toUpperCase(Locale.getDefault());
      columnNameConfigMap.put(columnName, mapping);
    }
  }

  /**
   * @return An instance of an empty DataObject Builder.
   */
  protected abstract FieldBasedDataObject.Builder<T> newBuilder();

  @Override
  public T extract(Cursor cursor, Query query) {
    FieldBasedDataObject.Builder<T> builder = newBuilder();

    int numColumns = cursor.getColumnCount();
    for (int i = 0; i < numColumns; i++) {
      String columnName = cursor.getColumnName(i).toUpperCase(Locale.getDefault());
      FieldColumnConfig config = columnNameConfigMap.get(columnName);
      if (config != null) {
        ColumnValueExtractor<?> extractor = config.getExtractor();
        Column column = config.getColumn();
        Object value = extractor.extract(cursor, column);
        builder.setFieldValue(config.getField(), value);
      }
    }
    return builder.build();
  }
}
