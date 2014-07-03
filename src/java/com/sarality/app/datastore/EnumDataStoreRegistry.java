package com.sarality.app.datastore;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry for all EnumDataStores in the System.
 * <p>
 * Can be used to create global instances that can be initialized only once and stored with the Application instance.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumDataStoreRegistry {
  // Internal map between Name of EnumDataStore and the EnumDataStore instance.
  private final Map<String, EnumDataStore<?>> registry = new HashMap<String, EnumDataStore<?>>();

  /**
   * Store a DataStore with the registry.
   * 
   * @param store EnumDataStore that needs to be registered.
   */
  public void register(EnumDataStore<?> store) {
    if (!store.isInitialized()) {
      // We assume that init method takes care of synchronization to prevent the
      // store from being initialize twice.
      store.init();
    }
    registry.put(store.getDataStoreName(), store);
  }

  /**
   * Returns the EnumDataStore with the given name.
   * 
   * @param name Name of the EnumDataStore that is being looked up.
   * @return EnumDataStore with the given name.
   */
  public EnumDataStore<?> getEnumDataStore(String name) {
    return registry.get(name);
  }
}
