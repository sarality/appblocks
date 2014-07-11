package com.sarality.app.config;

import java.util.List;

import android.app.Application;

import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.EnumDataStore;
import com.sarality.app.datastore.db.Table;

/**
 * Interface for an Initializer for a Module.
 * <p>
 * Creates Tables, DataStores and EnumDataStores that can then be registered with the Application.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface ModuleInitializer {

  /**
   * Create a List of Tables and returns them so that they are accessible via the applications as globals.
   * 
   * @return {@code List} of {@link Table}s that need to be registered with the global registry.
   */
  public List<Table<?>> getTables(Application application);

  /**
   * Create a List of DataStores and returns them so that they are accessible via the applications as globals.
   * 
   * @return {@code List} of {@link DataStore}s that need to be registered with the global registry.
   */
  public List<DataStore<?>> getDataStores(Application application);

  /**
   * Create a List of EnumDataStores and returns them so that they are accessible via the applications as globals.
   * 
   * @return {@code List} of {@link EnumDataStore}s that need to be registered with the global registry.
   */
  public List<EnumDataStore<?>> getEnumDataStores(Application application);
}
