package com.sarality.app.datastore.db;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.WritableDataStore;

/**
 * Interface for all Tables of a database that provide the ability to store, query, update and delete data objects.
 * <p>
 * The Table can have In Memory implementations for testing purposes.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> Data associated with each row of the table
 */
public interface Table<T> extends WritableDataStore<T, Long> {

  /**
   * @return String name for the database.
   */
  public String getDatabaseName();

  /**
   * @return String name for the Table.
   */
  public String getTableName();

  /**
   * @return Integer version for the Table.
   */
  public int getTableVersion();

  /**
   * @return Column and Primary Key information about the Table.
   */
  public TableInfo getTableInfo();

  public void setListener(TableListenerRegistryConfig<T> listenerConfig);
}
