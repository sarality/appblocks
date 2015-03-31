package com.sarality.app.datastore.column;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;

/**
 * Processes extraction and population of a Column that stores data for an Enum.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumColumnProcessor<T extends Enum<T>> implements ColumnProcessor<T> {

  private Class<T> enumType;

  public EnumColumnProcessor(Class<T> enumType) {
    this.enumType = enumType;
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
    return Enum.valueOf(enumType, stringValue);
  }

  @Override
  public void populate(ContentValues contentValues, Column column, T value) {
    if (value != null) {
      contentValues.put(column.getName(), value.name());
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
            + fieldValue.getValueClass() + " to " + enumType + " while adding value for column " + column.getName());
      }
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType
          + " to ENUM while adding value for column " + column.getName());
    }
  }
}
