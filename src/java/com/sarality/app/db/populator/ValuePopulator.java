package com.sarality.app.db.populator;

import com.sarality.app.db.Column;

import android.content.ContentValues;

/**
 * Interface for all classes that add a value to a {@code ContentValues} instance.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T>
 */
public interface ValuePopulator<T> {

  /**
   * Populate the given column with the given value.
   * <p>
   * The method also contains a Pre-Condition that allows the caller to specify the condition 
   * that must be true before the value is set. This allows us to check if the data for this column 
   * exists in the enclosing data object before the value is set.
   * 
   * @param contentValues The ContentValues to be populated
   * @param column The column for which a value is being added
   * @param value The value for the column for which the value is being added
   * @param valueExistsPreCondition The condition that must be true before the value should be set in
   * the ContentValues.
   */
  public void populate(ContentValues contentValues, Column column, T value,
      boolean valueExistsPreCondition);

}
