package com.sarality.app.datastore;

import com.sarality.app.data.EnumData;

/**
 * Interface for DataStore that store {@link EnumData}.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <E> Type of Enum that is store in the data store
 */
public interface EnumDataStore<E extends EnumData> {

  /**
   * @return String name for the DataStore.
   */
  public String getDataStoreName();

  /**
   * Initialize the DataStore.
   */
  public void init();

  /**
   * @return {@code true} is the DataStore has been initialized, {@code false} otherwise.
   */
  public boolean isInitialized();

  /**
   * Return the Enum value for the given name.
   * 
   * @param name String name to lookup the enum for.
   * @return Enum value with given name.
   */
  public E lookupByName(String name);
}
