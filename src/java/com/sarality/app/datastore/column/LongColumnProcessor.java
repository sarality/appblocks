package com.sarality.app.datastore.column;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;

/**
 * Processes extraction and population of a Column that stores Long data.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class LongColumnProcessor implements ColumnProcessor<Long> {

  @Override
  public Long extract(Cursor cursor, Column column) {
    int columnIndex = cursor.getColumnIndex(column.getName());
    if (columnIndex < 0) {
      throw new IllegalArgumentException("Column with name " + column.getName() + " not found.");
    }
    if (cursor.isNull(columnIndex)) {
      return null;
    }
    try {
      return cursor.getLong(columnIndex);
    } catch (NumberFormatException e) {
      String value = cursor.getString(columnIndex);
      throw new IllegalStateException("Cannot extract Long value from Column " + column.getName()
          + ". Cannot convert value stored in column to a Long : " + value);
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, Long value) {
    contentValues.put(column.getName(), value);
  }

  @Override
  public void populate(ContentValues contentValues, Column column, FieldValue<?> fieldValue) {
    if (fieldValue == null || !fieldValue.hasValue() || fieldValue.getValue() == null) {
      contentValues.putNull(column.getName());
      return;
    }

    Field.DataType dataType = fieldValue.getDataType();
    if (dataType == Field.DataType.LONG) {
      Long value = (Long) fieldValue.getValue();
      populate(contentValues, column, value);
    } else if (dataType == Field.DataType.INTEGER) {
      Integer value = (Integer) fieldValue.getValue();
      populate(contentValues, column, value.longValue());
    } else {
      throw new IllegalArgumentException("Cannot convert data for type " + dataType
          + " to Long while adding value for column " + column.getName());
    }
  }
}
