package com.sarality.app.datastore.column;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;

/**
 * Processes extraction and population of a Column that stores Double data.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DoubleColumnProcessor implements ColumnProcessor<Double> {

  @Override
  public Double extract(Cursor cursor, Column column) {
    int columnIndex = cursor.getColumnIndex(column.getName());
    if (columnIndex < 0) {
      throw new IllegalArgumentException("Column with name " + column.getName() + " not found.");
    }
    if (cursor.isNull(columnIndex)) {
      return null;
    }
    try {
      return cursor.getDouble(columnIndex);
    } catch (NumberFormatException e) {
      String value = cursor.getString(columnIndex);
      throw new IllegalStateException("Cannot extract Double value from Column " + column.getName()
          + ". Cannot convert value stored in column to a Double : " + value);
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, Double value) {
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
    if (dataType == Field.DataType.DOUBLE) {
      Double value = (Double) fieldValue.getValue();
      populate(contentValues, column, value);
    } else if (dataType == Field.DataType.LONG) {
      Long value = (Long) fieldValue.getValue();
      populate(contentValues, column, value.doubleValue());
    } else if (dataType == Field.DataType.INTEGER) {
      Integer value = (Integer) fieldValue.getValue();
      populate(contentValues, column, value.doubleValue());
    } else {
      throw new IllegalArgumentException("Cannot convert data for type " + dataType
          + " to Double while adding value for column " + column.getName());
    }
  }
}
