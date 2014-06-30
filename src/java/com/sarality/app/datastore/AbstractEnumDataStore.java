package com.sarality.app.datastore;

import java.util.HashMap;
import java.util.Map;

import com.sarality.app.data.EnumData;

/**
 * Base implementation of a DataStore that store {@link EnumData}.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <E> Type of Enum stored by the DataStore.
 */
public abstract class AbstractEnumDataStore<E extends EnumData> implements EnumDataStore<E> {

  // Map between Enum name and data for the Enum.
  private final Map<String, E> dataStore = new HashMap<String, E>();

  // Name of the data store.
  private final String storeName;

  // Indicates whether the data store has been initialized or not
  private boolean isInitialized = false;

  /**
   * Constructor.
   * 
   * @param storeName String name of the DataStore.
   */
  public AbstractEnumDataStore(String storeName, EnumDataProvider<E>[] predefinedValues) {
    this.storeName = storeName;
    for (EnumDataProvider<E> predefinedValue : predefinedValues) {
      addData(predefinedValue.getEnumData());
    }
  }

  @Override
  public String getDataStoreName() {
    return storeName;
  }

  @Override
  public boolean isInitialized() {
    return isInitialized;
  }

  @Override
  public void init() {
    // Avoids running the initialization twice in case of a race condition.
    synchronized (this) {
      if (!isInitialized) {
        loadData();
        isInitialized = true;
      }
    }
  }

  /**
   * Loads the data for the DataStore.
   */
  protected abstract void loadData();

  @Override
  public E lookupByName(String name) {
    return dataStore.get(name);
  }

  /**
   * Add an entry to the DataStore.
   * 
   * @param data EnumData to be added to the store.
   */
  protected void addData(E data) {
    dataStore.put(data.getEnumName(), data);
  }
}
