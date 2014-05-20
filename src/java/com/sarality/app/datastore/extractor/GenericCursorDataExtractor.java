package com.sarality.app.datastore.extractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.sarality.app.data.FieldBasedDataObject;
import com.sarality.app.datastore.FieldColumnConfig;
import com.sarality.app.datastore.Query;

public abstract class GenericCursorDataExtractor<T extends FieldBasedDataObject<T>> implements CursorDataExtractor<T> {
  private Map<String, FieldColumnConfig> columnNameConfigMap = new HashMap<String, FieldColumnConfig>();

  public GenericCursorDataExtractor(List<FieldColumnConfig> mappingList) {
    for (FieldColumnConfig mapping : mappingList) {
      columnNameConfigMap.put(mapping.getColumn().getName(), mapping);
    }
  }

  protected abstract FieldBasedDataObject.Builder<T> newBuilder();

  @Override
  public T extract(Cursor cursor, Query query) {
    FieldBasedDataObject.Builder<T> builder = newBuilder();
    
    int numColumns = cursor.getColumnCount();
    for (int i = 0; i < numColumns; i++) {
      String columnName = cursor.getColumnName(i);
      FieldColumnConfig config = columnNameConfigMap.get(columnName);
      Object value = config.getExtractor().extract(cursor, config.getColumn());
      builder.setFieldValue(config.getField(), value);
    }
    return builder.build();
  }
}
