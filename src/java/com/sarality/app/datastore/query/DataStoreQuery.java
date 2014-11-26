package com.sarality.app.datastore.query;

import java.util.List;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.DataStore;
import com.sarality.app.datastore.DataStoreRegistry;

/**
 * Wrapper to query a dataStore
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DataStoreQuery {

  /**
   * Queries the DataStore given the name of the DataStore and name of the primary key
   * 
   * @param dataStoreName : Name of the data Store
   * @param primaryKey : Primary key Column
   * @param primaryKeyValue : Value
   * @param <T> : DataType within the DataStore
   * @return : Returns the list of dataType T based on the query
   */
  public static <T> List<T> queryByPrimaryKey(String dataStoreName, Column primaryKey, Long... primaryKeyValue) {
    DataStoreRegistry dataStoreRegistry = DataStoreRegistry.getGlobalInstance();
    @SuppressWarnings("unchecked")
    DataStore<T> dataStore = (DataStore<T>) dataStoreRegistry.getDataStore(dataStoreName);

    int count = 0;
    QueryBuilder queryBuilder = new QueryBuilder(dataStore);
    for (Long id : primaryKeyValue) {
      if (count == 0) {
        queryBuilder.where(primaryKey, Operator.EQUALS, FilterValue.of(id));
      } else {
        queryBuilder.or(primaryKey, Operator.EQUALS, FilterValue.of(id));
      }
      count++;
    }
    dataStore.open();
    List<T> data = dataStore.query(queryBuilder.build());
    dataStore.close();
    return data;
  }
}
