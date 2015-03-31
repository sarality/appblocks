package com.sarality.app.datastore.db;

import java.util.List;

import com.sarality.app.base.registry.MultiKeyRegistry;
import com.sarality.app.base.registry.Registry.Entry;
import com.sarality.app.data.DataObject;

public class TableListenerRegistryConfig<T> extends
    MultiKeyRegistry.EntryProvider<String, TableListener<T>> {

  public void addListener(TableListener<T> listener, String dataSourceTableName) {
    addEntry(listener, dataSourceTableName);
  }

  public void listener(T data, Table<T> datasourceTable) {
    List<Entry<String, TableListener<T>>> list = this.provide();

    for (int i = 0; i < list.size(); i++) {
      Entry<String, TableListener<T>> entry = list.get(i);
      if (entry.getKey() != null && entry.getKey().equals(datasourceTable.getTableName())) {
        entry.getValue().OnEntryUpdated(data);
      }
    }
  }
}
