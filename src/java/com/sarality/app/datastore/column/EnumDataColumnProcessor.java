package com.sarality.app.datastore.column;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.EnumData;
import com.sarality.app.data.EnumDataRegistry;
import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;

/**
 * Processes extraction and population of a Column that stores data for an EnumData.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumDataColumnProcessor<T extends EnumData<T>> implements ColumnProcessor<T> {

  private final Class<T> enumClass;
  private final EnumDataRegistry registry;

  public EnumDataColumnProcessor(Class<T> enumClass, EnumDataRegistry registry) {
    this.enumClass = enumClass;
    this.registry = registry;
  }

  public EnumDataColumnProcessor(Class<T> enumType) {
    this(enumType, EnumDataRegistry.getGlobalInstance());
  }
  
  @Override
  public T extract(Cursor cursor, Column column) {
    int columnIndex = cursor.getColumnIndex(column.getName());
    if (cursor.isNull(columnIndex)) {
      return null;
    }
    String stringValue = cursor.getString(columnIndex);
    return registry.valueOf(enumClass, stringValue);
  }

  @Override
  public void populate(ContentValues contentValues, Column column, T value) {
    if (value != null) {
      contentValues.put(column.getName(), value.getEnumName());
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
    if (dataType == Field.DataType.ENUM_DATA) {
      try {
        @SuppressWarnings("unchecked")
        T value = (T) fieldValue.getValue();
        populate(contentValues, column, value);
      } catch (ClassCastException cce) {
        throw new IllegalArgumentException(" Cannot convert data for type " + dataType + " and class "
            + fieldValue.getValueClass() + " to " + enumClass + " while adding value for column " + column.getName());
      }
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType
          + " to ENUM_DATA while adding value for column " + column.getName());
    }
  }
}
