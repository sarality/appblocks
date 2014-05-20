package com.sarality.app.datastore.populator;

import android.content.ContentValues;

import com.sarality.app.data.FieldData;
import com.sarality.app.datastore.Column;

public class DoubleValuePopulator implements ValuePopulator<Double>, FieldDataValuePopulator {

  @Override
  public void populate(ContentValues contentValues, Column column, Double value,
      boolean valueExistsPreCondition) {
    if (valueExistsPreCondition && value != null) {
      contentValues.put(column.getName(), value);
    }
  }

  @Override
  public void populate(ContentValues values, Column column, FieldData<?> data) {
    FieldData.Type dataType = data.getType();
    if (dataType == FieldData.Type.DOUBLE) {
      populate(values, column, (Double) data.getValue(), data.hasValue());
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType 
          + " to LONG while adding value for column " + column.getName());
    }
  }

}
