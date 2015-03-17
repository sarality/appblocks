package com.sarality.app.datastore;

import java.util.HashMap;
import java.util.Map;

/**
 * A Registry for all Data Stores.
 * <p>
 * Data Stores are registered with their given name which us assumed to be unique.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DataStoreRegistry {
  private static final DataStoreRegistry GLOBAL_INSTANCE = new DataStoreRegistry();

  private final Map<String, DataStore<?>> registry = new HashMap<String, DataStore<?>>();

  /**
   * Register the given data store.
   * 
   * @param dataStore Data Store to be registered.
   * @throws IllegalStateException If a datastore with the same name has already been registered.
   */
  public void register(DataStore<?> dataStore) {
    String storeName = dataStore.getName();
    if (registry.containsKey(storeName)) {
      throw new IllegalStateException("Registering Multiple DataStores with the name " + storeName);
    }
    registry.put(storeName, dataStore);
  }

  /**
   * Retrieve the Data Store with the given name.
   * 
   * @param name String name of the data store.
   * @return Data Store with the given name.
   * @throws IllegalArgumentException if no data store was registered with given name.
   */
  public DataStore<?> getDataStore(String name) {
    DataStore<?> dataStore = registry.get(name);
    if (dataStore == null) {
      throw new IllegalArgumentException("No data store found with name " + name);
    }
    return dataStore;
  }

  /**
   * @return Global instance of the DataStoreRegistry.
   */
  public static DataStoreRegistry getGlobalInstance() {
    return GLOBAL_INSTANCE;
  }
}
