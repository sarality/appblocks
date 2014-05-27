package com.sarality.app.datastore.db;

import com.sarality.app.data.DataObject;

/**
 * Interface for all classes that listen to updates on the table.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 * @param <T>
 * @param <K>
 */
public interface TableListener<T extends DataObject<T>> {

  /**
   * Update the schema for the given table in the given database
 * @param <T>
   * 
   * @param db The database to be updated
   * @param table The table that needs to be updated with its current schema
   */

	public void OnEntryUpdated(T data);

}
