package com.sarality.app.datastore.extractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.sarality.app.data.FieldBasedDataObject;
import com.sarality.app.datastore.FieldColumnMapping;
import com.sarality.app.datastore.Query;

public abstract class GenericCursorDataExtractor<T extends FieldBasedDataObject<T>> implements CursorDataExtractor<T> {
  private Map<String, FieldColumnMapping> columnMappingMap = new HashMap<String, FieldColumnMapping>();

  public GenericCursorDataExtractor(List<FieldColumnMapping> mappingList) {
    for (FieldColumnMapping mapping : mappingList) {
      columnMappingMap.put(mapping.getColumn().getName(), mapping);
    }
  }

  protected abstract FieldBasedDataObject.Builder<T> newBuilder();

  @Override
  public T extract(Cursor cursor, Query query) {
    FieldBasedDataObject.Builder<T> builder = newBuilder();
    
    int numColumns = cursor.getColumnCount();
    for (int i = 0; i < numColumns; i++) {
      String columnName = cursor.getColumnName(i);
      FieldColumnMapping mapping = columnMappingMap.get(columnName);
      Object value = mapping.getExtractor().extract(cursor, mapping.getColumn());
      builder.setFieldValue(mapping.getField(), value);
    }
    return builder.build();
  }
}
