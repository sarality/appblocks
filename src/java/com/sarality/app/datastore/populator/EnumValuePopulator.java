package com.sarality.app.datastore.populator;

import android.content.ContentValues;

import com.sarality.app.data.FieldData;
import com.sarality.app.datastore.Column;

/**
 * Populates a ContentValues instance with data for an Enum Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumValuePopulator implements ValuePopulator<Enum<?>>, FieldDataValuePopulator {

  @Override
  public void populate(ContentValues contentValues, Column column, Enum<?> value,
      boolean valueExistsPreCondition) {
    if (valueExistsPreCondition && value != null) {
      contentValues.put(column.getName(), value.name());
    }
  }

  @Override
  public void populate(ContentValues values, Column column, FieldData<?> data) {
    FieldData.Type dataType = data.getType();
    if (dataType == FieldData.Type.ENUM) {
      populate(values, column, (Enum<?>) data.getValue(), data.hasValue());
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType 
          + " to ENUM while adding value for column " + column.getName());
    }
  }
}
