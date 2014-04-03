package com.sarality.app.datastore.populator;

import android.content.ContentValues;

import com.sarality.app.datastore.Column;

/**
 * Populates a ContentValues instance with data for an Enum Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumValuePopulator implements ValuePopulator<Enum<?>>{

  @Override
  public void populate(ContentValues contentValues, Column column, Enum<?> value,
      boolean valueExistsPreCondition) {
    if (valueExistsPreCondition && value != null) {
      contentValues.put(column.getName(), value.name());
    }
  }
}
