package com.sarality.app.datastore.column;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;

/**
 * Processes extraction and population of a Column that stores Boolean data.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class BooleanColumnProcessor implements ColumnProcessor<Boolean> {

  @Override
  public Boolean extract(Cursor cursor, Column column) {
    int columnIndex = cursor.getColumnIndex(column.getName());
    if (cursor.isNull(columnIndex)) {
      return null;
    }
    
    try {
      int intValue = cursor.getInt(columnIndex);
      if (intValue != 0 && intValue != 1) {
        throw new NumberFormatException("A Boolean Column must have the value 0 or 1");
      }
      return (intValue == 1);
    } catch (NumberFormatException e) {
      String value = cursor.getString(columnIndex);
      throw new IllegalStateException("Cannot extract Boolean value from Column " + column.getName()
          + ". Cannot convert value stored in column to a Boolean : " + value);
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, Boolean value) {
    if (value == null) {
      contentValues.putNull(column.getName());
    } else {
      contentValues.put(column.getName(), value);
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, FieldValue<?> fieldValue) {
    if (fieldValue == null || !fieldValue.hasValue() || fieldValue.getValue() == null) {
      contentValues.putNull(column.getName());
      return;
    }

    Field.DataType dataType = fieldValue.getDataType();
    if (dataType == Field.DataType.BOOLEAN) {
      Boolean value = (Boolean) fieldValue.getValue();
      populate(contentValues, column, value);
    } else {
      throw new IllegalArgumentException("Cannot convert data for type " + dataType 
          + " to Boolean while adding value for column " + column.getName());
    }
  }
}
