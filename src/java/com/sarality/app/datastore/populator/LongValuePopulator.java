package com.sarality.app.datastore.populator;

import android.content.ContentValues;

import com.sarality.app.data.FieldData;
import com.sarality.app.datastore.Column;

/**
 * Populates a ContentValues instance with data for a Long Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class LongValuePopulator implements ValuePopulator<Long>, FieldDataValuePopulator {

  @Override
  public void populate(ContentValues contentValues, Column column, Long value,
      boolean valueExistsPreCondition) {
    if (valueExistsPreCondition && value != null) {
      contentValues.put(column.getName(), value);
    }
  }

  @Override
  public void populate(ContentValues values, Column column, FieldData<?> data) {
    FieldData.Type dataType = data.getType();
    if (dataType == FieldData.Type.LONG) {
      populate(values, column, (Long) data.getValue(), data.hasValue());
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType 
          + " to LONG while adding value for column " + column.getName());
    }
  }
}
