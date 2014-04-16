package com.sarality.app.datastore.extractor;

import java.util.HashMap;
import java.util.Map;

import android.database.Cursor;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.MappedEnum;

public class MappedEnumValueExtractor<V, T extends MappedEnum<V>> implements ColumnValueExtractor<T> {
  
  private Map<V, T> enumMap = new HashMap<V, T>();

  public MappedEnumValueExtractor(T[] enumValues) {
    for (T enumValue : enumValues) {
      enumMap.put(enumValue.getMappedValue(), enumValue);
    }
  }

  public MappedEnumValueExtractor<V, T> withMapping(V value, T enumValue) {
    enumMap.put(value, enumValue);
    return this;
  }

  @Override
  public T extract(Cursor cursor, Column column) {
    if (column.getDataType() == Column.DataType.INTEGER) {
      int intValue = cursor.getInt(cursor.getColumnIndex(column.getName()));
      return enumMap.get(intValue);      
    } else if (column.getDataType() == Column.DataType.TEXT) {
      String stringValue = cursor.getString(cursor.getColumnIndex(column.getName()));
      return enumMap.get(stringValue);      
    }
    return null;
  }
}
