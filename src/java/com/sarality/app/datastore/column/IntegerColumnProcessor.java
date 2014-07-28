package com.sarality.app.datastore.column;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;

/**
 * Processes extraction and population of a Column that stores Integer data.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class IntegerColumnProcessor implements ColumnProcessor<Integer> {

  @Override
  public Integer extract(Cursor cursor, Column column) {
    int columnIndex = cursor.getColumnIndex(column.getName());
    if (cursor.isNull(columnIndex)) {
      return null;
    }
    try {
      return cursor.getInt(cursor.getColumnIndex(column.getName()));
    } catch (NumberFormatException e) {
      String value = cursor.getString(columnIndex);
      throw new IllegalStateException("Cannot extract Integer value from Column " + column.getName()
          + ". Cannot convert value stored in column to a Integer : " + value);
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, Integer value) {
    contentValues.put(column.getName(), value);
  }

  @Override
  public void populate(ContentValues contentValues, Column column, FieldValue<?> fieldValue) {
    if (fieldValue == null || !fieldValue.hasValue() || fieldValue.getValue() == null) {
      contentValues.putNull(column.getName());
      return;
    }

    Field.DataType dataType = fieldValue.getDataType();
    if (dataType == Field.DataType.INTEGER) {
      Integer value = (Integer) fieldValue.getValue();
      populate(contentValues, column, value);
    } else {
      throw new IllegalArgumentException("Cannot convert data for type " + dataType
          + " to Integer while adding value for column " + column.getName());
    }
  }
}
