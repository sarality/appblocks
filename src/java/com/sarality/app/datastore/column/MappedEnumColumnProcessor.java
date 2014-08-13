package com.sarality.app.datastore.column;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.MappedEnum;

/**
 * Processes extraction and population of a Column that stores data for an Enum.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class MappedEnumColumnProcessor<V, T extends MappedEnum<V>> implements ColumnProcessor<T> {

  private final Map<String, T> enumMap = new HashMap<String, T>();
  
  public MappedEnumColumnProcessor(Class<T> enumType, T[] enumValues) {
    for (T enumValue : enumValues) {
      enumMap.put(String.valueOf(enumValue.getMappedValue()), enumValue);
    }
  }

  @Override
  public T extract(Cursor cursor, Column column) {
    int columnIndex = cursor.getColumnIndex(column.getName());
    if (columnIndex < 0) {
      throw new IllegalArgumentException("Column with name " + column.getName() + " not found.");
    }
    if (cursor.isNull(columnIndex)) {
      return null;
    }
    String stringValue = cursor.getString(columnIndex);
    return enumMap.get(stringValue);      
  }

  @Override
  public void populate(ContentValues contentValues, Column column, T value) {
    if (value != null) {
      ColumnDataType dataType = column.getSpec().getDataType();
      String stringValue = String.valueOf(value.getMappedValue());
      if (dataType == ColumnDataType.TEXT) {
        contentValues.put(column.getName(), stringValue);
      } else if (dataType == ColumnDataType.INTEGER) {
        contentValues.put(column.getName(), Integer.valueOf(stringValue));        
      }
    } else {
      contentValues.putNull(column.getName());
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, FieldValue<?> fieldValue) {
    if (fieldValue == null || !fieldValue.hasValue() || fieldValue.getValue() == null) {
      contentValues.putNull(column.getName());
      return;
    }

    Field.DataType dataType = fieldValue.getDataType();
    if (dataType == Field.DataType.ENUM) {
      try {
        @SuppressWarnings("unchecked")
        T value = (T) fieldValue.getValue();
        populate(contentValues, column, value);
      } catch (ClassCastException cce) {
        throw new IllegalArgumentException(" Cannot convert data for type " + dataType + " and class "
            + fieldValue.getValueClass() + " to enum while adding value for column " + column.getName());
      }
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType
          + " to ENUM while adding value for column " + column.getName());
    }
  }
}
