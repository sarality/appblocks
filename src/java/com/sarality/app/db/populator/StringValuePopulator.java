package com.sarality.app.db.populator;

import android.content.ContentValues;

import com.sarality.app.db.Column;

/**
 * Populates a ContentValues instance with data for a String Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class StringValuePopulator implements ValuePopulator<String> {

  @Override
  public void populate(ContentValues contentValues, Column column, String value,
      boolean valueExistsPreCondition) {
    if (valueExistsPreCondition) {
      contentValues.put(column.getName(), value);
    }
  }
  
}
