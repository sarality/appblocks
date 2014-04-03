package com.sarality.app.datastore.populator;

import android.content.ContentValues;

import com.sarality.app.datastore.Column;

/**
 * Populates a ContentValues instance with data for a Long Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class LongValuePopulator implements ValuePopulator<Long> {

  @Override
  public void populate(ContentValues contentValues, Column column, Long value,
      boolean valueExistsPreCondition) {
    if (valueExistsPreCondition && value != null) {
      contentValues.put(column.getName(), value);
    }
  }
}
