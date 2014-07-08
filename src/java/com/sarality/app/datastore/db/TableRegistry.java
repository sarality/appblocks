package com.sarality.app.datastore.db;

import java.util.HashMap;
import java.util.Map;

public class TableRegistry {
  private final Map<String, Table<?>> registry = new HashMap<String, Table<?>>();
  
  public void register(Table<?> table) {
    registry.put(table.getTableName(), table);
  }

  public Table<?> getTable(String name) {
    return registry.get(name);
  }
}
