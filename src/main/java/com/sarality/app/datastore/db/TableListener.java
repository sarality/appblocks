package com.sarality.app.datastore.db;

import com.sarality.app.data.DataObject;

/**
 * Interface for all classes that listen to updates on the table.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 * @param <T>
 */
public interface TableListener<T> {

  /**
   * Update the schema for the given table in the given database
   * 
   * @param data The data
   */
  public void OnEntryUpdated(T data);

}
