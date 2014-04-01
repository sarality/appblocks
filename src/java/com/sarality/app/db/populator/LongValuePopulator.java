package com.sarality.app.db.populator;

import android.content.ContentValues;

import com.sarality.app.db.Column;

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
