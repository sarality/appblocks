package com.sarality.app.datastore;

import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableRegistry;
import com.sarality.app.datastore.query.BaseQueryBuilder;
import com.sarality.app.datastore.query.FilterValue;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.query.QueryGenerator;

import java.util.List;

/**
 * Builds and executes a Query on a DataStore.
 *
 * @author abhideep@ (Abhidep Singh)
 */
public class DataStoreQueryExecutor<T> extends BaseQueryBuilder<DataStoreQueryExecutor<T>> {

  private final DataStore<T> dataStore;

  public DataStoreQueryExecutor(DataStore<T> dataStore) {
    this.dataStore = dataStore;
  }

  @SuppressWarnings("unchecked")
  public DataStoreQueryExecutor(String tableName, TableRegistry registry) {
    this((Table<T>) registry.getTable(tableName));
  }

  public DataStoreQueryExecutor(String tableName) {
    this(tableName, TableRegistry.getGlobalInstance());
  }

  public List<T> execute() {
    Query query = QueryGenerator.build(this, dataStore.getColumns());
    try {
      dataStore.open();
      return dataStore.query(query);
    } finally {
      dataStore.close();
    }
  }

  public static <T> T queryByPrimaryKey(String dataStoreName, Column primaryKey, FilterValue<?> uniqueColumnValue) {
    DataStoreQueryExecutor<T> executor = new DataStoreQueryExecutor<T>(dataStoreName);
    List<T> data = executor.where(primaryKey).equals(uniqueColumnValue).execute();
    if (data.size() == 1) {
      return data.get(0);
    } else if (data.size() == 0) {
      return null;
    } else {
      throw new IllegalStateException("Too many rows returned for the query. Query on Column " + primaryKey.getName()
          + " returned " + data.size() + " rows");
    }
  }

  protected DataStoreQueryExecutor<T> getInstance() {
    return this;
  }

}
