package com.sarality.app.datastore.populator;

import android.content.ContentValues;

import com.sarality.app.data.FieldData;
import com.sarality.app.datastore.Column;

/**
 * Populates a ContentValues instance with data for a String Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class StringValuePopulator implements ValuePopulator<String>, FieldDataValuePopulator {

  @Override
  public void populate(ContentValues contentValues, Column column, String value,
      boolean valueExistsPreCondition) {
    if (valueExistsPreCondition) {
      contentValues.put(column.getName(), value);
    }
  }

  @Override
  public void populate(ContentValues values, Column column, FieldData<?> data) {
    FieldData.Type dataType = data.getType();
    if (dataType == FieldData.Type.STRING) {
      populate(values, column, (String) data.getValue(), data.hasValue());
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType 
          + " to STRING while adding value for column " + column.getName());
    }
  }

}
