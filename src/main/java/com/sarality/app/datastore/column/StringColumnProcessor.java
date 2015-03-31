package com.sarality.app.datastore.column;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;

/**
 * Processes extraction and population of a Column that stores String data.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class StringColumnProcessor implements ColumnProcessor<String> {

  @Override
  public String extract(Cursor cursor, Column column) {
    int columnIndex = cursor.getColumnIndex(column.getName());
    if (columnIndex < 0) {
      throw new IllegalArgumentException("Column with name " + column.getName() + " not found.");
    }
    if (cursor.isNull(columnIndex)) {
      return null;
    }
    return cursor.getString(cursor.getColumnIndex(column.getName()));
  }

  @Override
  public void populate(ContentValues contentValues, Column column, String value) {
    contentValues.put(column.getName(), value);
  }

  @Override
  public void populate(ContentValues contentValues, Column column, FieldValue<?> fieldValue) {
    if (fieldValue == null || !fieldValue.hasValue() || fieldValue.getValue() == null) {
      contentValues.putNull(column.getName());
      return;
    }

    Field.DataType dataType = fieldValue.getDataType();
    if (dataType == Field.DataType.STRING) {
      String value = (String) fieldValue.getValue();
      populate(contentValues, column, value);
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType
          + " to Long while adding value for column " + column.getName());
    }
  }
}
