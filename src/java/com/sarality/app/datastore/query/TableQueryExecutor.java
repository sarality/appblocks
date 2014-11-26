package com.sarality.app.datastore.query;

import java.util.List;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableRegistry;

/**
 * Wrapper to query a Table.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class TableQueryExecutor {

  /**
   * Queries the DataStore given the name of the Table and name of the primary key
   * 
   * @param tableName       : Name of the Table
   * @param primaryKey      : Primary key Column
   * @param primaryKeyValue : Value
   * @param <T>             : DataType within the DataStore
   * @return                : Returns the data T based on the query
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
   * @param tableName       : Name of the Table
   * @param primaryKey      : Primary key Column
   * @param primaryKeyValue : list of Values
   * @param <T>             : DataType within the DataStore
   * @return                : Returns the list of dataType T based on the query
   */
  public static <T extends DataObject<T>> List<T> queryByPrimaryKeys(String tableName, Column primaryKey,
      List<Long> primaryKeyValue) {
    TableRegistry registry = TableRegistry.getGlobalInstance();
    @SuppressWarnings("unchecked")
    Table<T> table = (Table<T>) registry.getTable(tableName);

    int count = 0;
    //TODO Change to usage of IN
    QueryBuilder queryBuilder = new QueryBuilder(table);
    for (Long id : primaryKeyValue) {
      if (count == 0) {
        queryBuilder.where(primaryKey, Operator.EQUALS, FilterValue.of(id));
      } else {
        queryBuilder.or(primaryKey, Operator.EQUALS, FilterValue.of(id));
      }
      count++;
    }
    try {
      table.open();
      List<T> data = (table.query(queryBuilder.build()));
      return data;
    } finally {
      table.close();
    }
  }
}
