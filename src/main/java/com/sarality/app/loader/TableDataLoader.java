package com.sarality.app.loader;

import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableRegistry;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.query.QueryBuilder;

import java.util.List;

/**
 * Utility class to load data from a Table.
 *
 * @param <T> Type of data returned by the Table.
 * @author abhideep@ (Abhidep Singh)
 */
public class TableDataLoader<T> {

  private final Table<T> table;
  private Query query;

  public TableDataLoader(String tableName, TableRegistry registry) {
    this.table = (Table<T>) registry.getTable(tableName);
  }

  public TableDataLoader(String tableName) {
    this(tableName, TableRegistry.getGlobalInstance());
  }

  public TableDataLoader<T> setQuery(QueryBuilder builder) {
    if (builder == null) {
      query = null;
    } else {
      this.query = builder.build();
    }
    return this;
  }

  public QueryBuilder newQuery() {
    return new QueryBuilder(table);
  }

  public List<T> loadAll() {
    try {
      table.open();
      return table.query(query);
    } finally {
      table.close();
    }
  }

  public T load() {
    List<T> dataList = loadAll();
    if (dataList != null && dataList.size() == 1) {
      return dataList.get(0);
    }
    if (dataList != null && dataList.size() > 1) {
      throw new IllegalArgumentException("Query returned more than one result. Use loadAll instead");
    }
    // If no data is returned then simply return null;
    return null;
  }
}
