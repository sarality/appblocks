package com.sarality.app.datastore.populator;

import android.content.ContentValues;

/**
 * Interface for all classes that populate a ContentValues object, which can then
 * be used to create an entry in a database table or update an existing row.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Data object that is used to populate the ContentValues object
 */
public interface ContentValuesPopulator<T> {

  /**
   * Populate the given ContentValues object with data from the given data object.
   * 
   * @param contentValues ContentValues object to populate
   * @param data The source of the information
   */
  public boolean populate(ContentValues contentValues, T data);
  
}
