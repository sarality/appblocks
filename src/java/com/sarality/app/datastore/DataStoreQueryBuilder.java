package com.sarality.app.datastore;

import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableRegistry;
import com.sarality.app.datastore.query.BaseQueryBuilder;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.query.QueryGenerator;

/**
 * Builds a Query to either query or update a DataStore.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DataStoreQueryBuilder<T> extends BaseQueryBuilder<DataStoreQueryBuilder<T>> {

  private final DataStore<T> dataStore;

  public DataStoreQueryBuilder(DataStore<T> dataStore) {
    this.dataStore = dataStore;
  }

  @SuppressWarnings("unchecked")
  public DataStoreQueryBuilder(String tableName, TableRegistry registry) {
    this((Table<T>) registry.getTable(tableName));
  }

  public DataStoreQueryBuilder(String tableName) {
    this(tableName, TableRegistry.getGlobalInstance());
  }

  @Override
  protected DataStoreQueryBuilder<T> getInstance() {
    return this;
  }

  public DataStore<T> getDataStore() {
    return dataStore;
  }

  /**
   * Builds the Query for the Query Builder.
   *
   * @return Query for the builder.
   */
  public Query build() {
    return QueryGenerator.build(this, dataStore.getColumns());
  }
}
