package com.sarality.app.datastore.query;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.DataStoreRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper to query a dataStore
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DataStoreQueryExecutor {

  /**
   * Queries the DataStore given the name of the DataStore and name of the primary key
   *
   * @param dataStoreName : Name of the DataStore
   * @param primaryKey : Primary key Column
   * @param primaryKeyValue : Value
   * @param <T> : DataType within the DataStore
   * @return : Returns the data T based on the query
   */
  public static <T extends DataObject<T>> T queryByPrimaryKey(String dataStoreName, Column primaryKey,
      Long primaryKeyValue) {
    DataStoreRegistry dataStoreRegistry = DataStoreRegistry.getGlobalInstance();
    @SuppressWarnings("unchecked")
    DataStore<T> dataStore = (DataStore<T>) dataStoreRegistry.getDataStore(dataStoreName);

    QueryBuilder queryBuilder = new QueryBuilder(dataStore);
    queryBuilder.where(primaryKey, Operator.EQUALS, FilterValue.of(primaryKeyValue));
    try {
      dataStore.open();
      List<T> data = (dataStore.query(queryBuilder.build()));
      if (data.size() > 1) {
        throw new IllegalStateException("Too many rows for a primary key " + data.size());
      }
      if (data.size() > 0) {
        return data.get(0);
      }
    } finally {
      dataStore.close();
    }
    return null;
  }

  /**
   * Queries the DataStore given the name of the DataStore and name of the primary key
   *
   * @param dataStoreName : Name of the data Store
   * @param primaryKey : Primary key Column
   * @param primaryKeyValues : list of values
   * @param <T> : DataType within the DataStore
   * @return : Returns the list of dataType T based on the query
   */
  public static <T> List<T> queryByPrimaryKeys(String dataStoreName, Column primaryKey, List<Long> primaryKeyValues) {
    DataStoreRegistry dataStoreRegistry = DataStoreRegistry.getGlobalInstance();
    @SuppressWarnings("unchecked")
    DataStore<T> dataStore = (DataStore<T>) dataStoreRegistry.getDataStore(dataStoreName);

    QueryBuilder queryBuilder = new QueryBuilder(dataStore);
    List<FilterValue<?>> values = new ArrayList<FilterValue<?>>();

    for (Long primaryKeyValue : primaryKeyValues) {
      values.add(FilterValue.of(primaryKeyValue));
    }
    queryBuilder.where(primaryKey, Operator.IN, values);
    try {
      dataStore.open();
      return dataStore.query(queryBuilder.build());
    } finally {
      dataStore.close();
    }
  }
}
