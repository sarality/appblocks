package com.sarality.app.datastore.db;

import java.util.HashMap;
import java.util.Map;

public class TableRegistry {
  private final Map<String, Table<?>> registry = new HashMap<String, Table<?>>();
  
  public void register(String name, Table<?> table) {
    registry.put(name, table);
  }

  public Table<?> getTable(String name) {
    return registry.get(name);
  }
}
