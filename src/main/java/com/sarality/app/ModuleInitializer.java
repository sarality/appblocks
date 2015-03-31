package com.sarality.app;

import java.util.List;

import android.app.Application;

import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.db.Table;

/**
 * Interface for an Initializer for a Module.
 * <p>
 * Creates Tables and DataStores that can then be registered with the Application. Also initializes any EnumDatas for
 * the module.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface ModuleInitializer {

  /**
   * Create a List of Tables and returns them so that they are accessible via the applications as globals.
   * 
   * @param application The Application being initialized
   * @return {@code List} of {@link Table}s that need to be registered with the global registry.
   */
  public List<Table<?>> getTables(Application application);

  /**
   * Create a List of DataStores and returns them so that they are accessible via the applications as globals.
   * 
   * @param application The Application being initialized
   * @return {@code List} of {@link DataStore}s that need to be registered with the global registry.
   */
  public List<DataStore<?>> getDataStores(Application application);

  /**
   * Initialize any EnumDatas in the module.
   * 
   * @param application The Application being initialized
   */
  public void initEnumDatas(Application application);
}
