package com.sarality.app.datastore.query;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper to query a Table.
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableQueryExecutor {

  /**
   * Queries the DataStore given the name of the Table and name of the primary key
   *
   * @param tableName : Name of the Table
   * @param primaryKey : Primary key Column
   * @param primaryKeyValue : Value
   * @param <T> : DataType within the DataStore
   * @return : Returns the data T based on the query
   */
  public static <T extends DataObject<T>> T queryByPrimaryKey(String tableName, Column primaryKey, Long primaryKeyValue) {
    TableRegistry registry = TableRegistry.getGlobalInstance();
    @SuppressWarnings("unchecked")
    Table<T> table = (Table<T>) registry.getTable(tableName);

    QueryBuilder queryBuilder = new QueryBuilder(table);
    queryBuilder.where(primaryKey, Operator.EQUALS, FilterValue.of(primaryKeyValue));
    try {
      table.open();
      List<T> data = (table.query(queryBuilder.build()));
      if (data.size() > 1) {
        throw new IllegalStateException("Too many rows for a primary key " + data.size());
      }
      if (data.size() > 0) {
        return data.get(0);
      }
    } finally {
      table.close();
    }
    return null;
  }

  /**
   * Queries the DataStore given the name of the Table and name of the primary key
   *
   * @param tableName : Name of the Table
   * @param primaryKey : Primary key Column
   * @param primaryKeyValues: list of Values
   * @param <T> : DataType within the DataStore
   * @return : Returns the list of dataType T based on the query
   */
  public static <T extends DataObject<T>> List<T> queryByPrimaryKeys(String tableName, Column primaryKey,
      List<Long> primaryKeyValues) {
    TableRegistry registry = TableRegistry.getGlobalInstance();
    @SuppressWarnings("unchecked")
    Table<T> table = (Table<T>) registry.getTable(tableName);

    QueryBuilder queryBuilder = new QueryBuilder(table);
    List<FilterValue<?>> values = new ArrayList<FilterValue<?>>();

    for (Long primaryKeyValue : primaryKeyValues) {
      values.add(FilterValue.of(primaryKeyValue));
    }
    queryBuilder.where(primaryKey, Operator.IN, values);
    try {
      table.open();
      return table.query(queryBuilder.build());
    } finally {
      table.close();
    }
  }

  /**
   * Queries the DataStore given the name of the Table , returns all values in the table
   *
   * @param tableName : Name of the Table
   * @param primaryKey : Primary key Column
   * @param <T> : DataType within the DataStore
   * @return : Returns the data T based on the query
   */
  public static <T extends DataObject<T>> List<T> queryPrimaryKeyNotNull(String tableName, Column primaryKey) {
    TableRegistry registry = TableRegistry.getGlobalInstance();
    @SuppressWarnings("unchecked")
    Table<T> table = (Table<T>) registry.getTable(tableName);

    QueryBuilder queryBuilder = new QueryBuilder(table);
    queryBuilder.where(primaryKey, Operator.IS_NOT_NULL);
    try {
      table.open();
      return table.query(queryBuilder.build());
    } finally {
      table.close();
    }
  }
}
