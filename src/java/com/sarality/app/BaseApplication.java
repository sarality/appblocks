package com.sarality.app;

import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.config.ModuleInitializer;
import com.sarality.app.data.EnumData;
import com.sarality.app.datastore.EnumDataStore;
import com.sarality.app.datastore.EnumDataStoreRegistry;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableRegistry;

/**
 * Base implementation for Applications that use Modules to define Tables and DataStores.
 * <p>
 * By default, the class has no logging or crash reporting capability. The implementation class can however initialize
 * the logging and crash reporting utilities they are using by overriding {@link #initLogging()}
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseApplication extends Application {

  private final TableRegistry tableRegistry = new TableRegistry();

  private final EnumDataStoreRegistry enumStoreRegistry = new EnumDataStoreRegistry();

  private final List<ModuleInitializer> moduleInitializerList = Lists.of();

  public static BaseApplication getApp(Context context) {
    return (BaseApplication) context.getApplicationContext();
  }

  public static BaseApplication getApp(Activity activity) {
    return (BaseApplication) activity.getApplication();
  }

  @Override
  public final void onCreate() {
    super.onCreate();
    initLogging();

    // Register all Modules.
    initModules();

    // Once all modules are registered then register the Tables and DataStore defined by the modules.
    initTables();
    initEnumDataStores();
    // TODO(abhideep) : Add something for Data Stores as well

    // Perform Application specific initialization.
    initApp();
  }

  /**
   * Initialize and Logging and Crash Reporting frameworks here.
   */
  protected abstract void initLogging();

  /**
   * Add all modules used by the Application here by making calls to {@link #addModuleInitializer(ModuleInitializer)}.
   */
  protected abstract void initModules();

  /**
   * Register a Module Initializer to the Application.
   * 
   * @param module ModuleInitializer for a module used by the Application.
   */
  protected void addModuleInitializer(ModuleInitializer module) {
    moduleInitializerList.add(module);
  }

  /**
   * Performs and Application specific initialization.
   */
  protected abstract void initApp();
  
  /**
   * Initialize all Tables provided by the ModuleInitialized registered with the Application.
   */
  private void initTables() {
    for (ModuleInitializer module : moduleInitializerList) {
      List<Table<?>> tableList = module.getTables(this);
      if (tableList != null) {
        for (Table<?> table : tableList) {
          tableRegistry.register(table);
        }
      }
    }
  }

  /**
   * Initialize all EnumDataStores provided by the ModuleInitialized registered with the Application.
   */
  private void initEnumDataStores() {
    for (ModuleInitializer module : moduleInitializerList) {
      List<EnumDataStore<?>> storeList = module.getEnumDataStores(this);
      if (storeList != null) {
        for (EnumDataStore<?> store : storeList) {
          store.init();
          enumStoreRegistry.register(store);
        }
      }
    }
  }

  /**
   * Retrieve the Table with the given name that has been registered with the Application.
   * 
   * @param name Name of the Table being retrieved.
   * @return Table with the given name.
   */
  public Table<?> getTable(String name) {
    return tableRegistry.getTable(name);
  }

  /**
   * Retrieve the EnumDataStore with the given name that has been registered with the Application.
   * 
   * @param name Name of the EnumDataStore being retrieved.
   * @return EnumDataStore with the given name.
   */
  public EnumDataStore<?> getEnumDataStore(String name) {
    return enumStoreRegistry.getEnumDataStore(name);
  }

  /**
   * Retrieve the EnumDataStore that stores EnumData of the given Class.
   * 
   * @param enumClass Class of the EnumData that is stored by the EnumDataStore being retrieved.
   * @return EnumDataStore that stores EnumData of the given class.
   */
  public <E extends EnumData> EnumDataStore<E> getEnumDataStore(Class<E> enumClass) {
    @SuppressWarnings("unchecked")
    EnumDataStore<E> store = (EnumDataStore<E>) enumStoreRegistry.getEnumDataStore(enumClass);
    return store;
  }
}
