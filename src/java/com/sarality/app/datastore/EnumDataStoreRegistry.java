package com.sarality.app.datastore;

import java.util.Map;

import com.sarality.app.common.collect.Maps;
import com.sarality.app.data.EnumData;

/**
 * A registry for all EnumDataStores in the System.
 * <p>
 * Can be used to create global instances that can be initialized only once and stored with the Application instance.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumDataStoreRegistry {
  // Internal map between Name of EnumDataStore and the EnumDataStore instance.
  private final Map<String, EnumDataStore<? extends EnumData>> registry = Maps.empty();
  private final Map<Class<? extends EnumData>, String> enumDataClassRegistry = Maps.empty();

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
    enumDataClassRegistry.put(store.getEnumClass(), store.getDataStoreName());
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

  /**
   * Returns the EnumDataStore for the given EnumData class.
   * 
   * @param enumClass Class of EnumData that is stored by the EnumDataStore that is being looked up.
   * @return EnumDataStore for the given EnumData.
   */
  public EnumDataStore<?> getEnumDataStore(Class<? extends EnumData> enumClass) {
    String dataStoreName = enumDataClassRegistry.get(enumClass);
    return registry.get(dataStoreName);
  }
}
