package com.sarality.app.datastore.db;

import java.util.HashMap;
import java.util.Map;

/**
 * A Registry for all Tables.
 * <p>
 * Tables are registered with their given name which us assumed to be unique.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class TableRegistry {
  private static final TableRegistry GLOBAL_INSTANCE = new TableRegistry();

  private final Map<String, Table<?>> registry = new HashMap<String, Table<?>>();

  /**
   * Register the given table.
   * 
   * @param table Table to be registered.
   * @throws IllegalStateException If a table with the same name has already been registered.
   */
  public void register(Table<?> table) {
    String tableName = table.getTableName();
    if (registry.containsKey(tableName)) {
      throw new IllegalStateException("Registring Multiple Table with the name " + tableName);
    }
    registry.put(table.getTableName(), table);
  }

  /**
   * Retrieve the Table with the given name.
   * 
   * @param name String name of the table.
   * @return Table with the given name.
   * @throws IllegalArgumentException if no table was registered with given name.
   */
  public Table<?> getTable(String name) {
    Table<?> table = registry.get(name);
    if (table == null) {
      throw new IllegalArgumentException("No table found with name " + name);
    }
    return table;
  }

  /**
   * @return Global instance of the TableRegistry.
   */
  public static TableRegistry getGlobalInstance() {
    return GLOBAL_INSTANCE;
  }
}
