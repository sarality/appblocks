package com.sarality.app.datastore.extractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.util.Log;

import com.sarality.app.data.FieldBasedDataObject;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.FieldColumnConfig;
import com.sarality.app.datastore.Query;

public abstract class GenericCursorDataExtractor<T extends FieldBasedDataObject<T>> implements CursorDataExtractor<T> {

  private static final String TAG = "GenericCursorDataExtractor";
  
  private Map<String, FieldColumnConfig> columnNameConfigMap = new HashMap<String, FieldColumnConfig>();

  public GenericCursorDataExtractor(List<FieldColumnConfig> mappingList) {
    for (FieldColumnConfig mapping : mappingList) {
      String columnName = mapping.getColumn().getName().toUpperCase();
      columnNameConfigMap.put(columnName, mapping);
    }
  }

  protected abstract FieldBasedDataObject.Builder<T> newBuilder();

  protected abstract String getDataStoreName();

  @Override
  public T extract(Cursor cursor, Query query) {
    FieldBasedDataObject.Builder<T> builder = newBuilder();

    int numColumns = cursor.getColumnCount();
    for (int i = 0; i < numColumns; i++) {
      String columnName = cursor.getColumnName(i).toUpperCase();
      FieldColumnConfig config = columnNameConfigMap.get(columnName);
      if (config == null) {
        Log.w(TAG, "No Config defined for Column " + columnName + ". No value being extracted");        
      } else {
        ColumnValueExtractor<?> extractor = config.getExtractor();
        Column column = config.getColumn();
        Object value = extractor.extract(cursor, column);
        builder.setFieldValue(config.getField(), value);
      }
    }
    return builder.build();
  }
}
