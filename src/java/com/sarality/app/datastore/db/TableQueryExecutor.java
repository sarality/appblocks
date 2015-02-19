package com.sarality.app.datastore.db;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.query.BaseQueryBuilder;
import com.sarality.app.datastore.query.FilterValue;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.query.QueryGenerator;

import java.util.List;

/**
 * Builds and executes a Query on a Table.
 *
 * @author abhideep@ (Abhidep Singh)
 */
public class TableQueryExecutor<T> extends BaseQueryBuilder<TableQueryExecutor<T>> {

  private final Table<T> table;

  public TableQueryExecutor(Table<T> table) {
    this.table = table;
  }

  @SuppressWarnings("unchecked")
  public TableQueryExecutor(String tableName, TableRegistry registry) {
    this((Table<T>) registry.getTable(tableName));
  }

  public TableQueryExecutor(String tableName) {
    this(tableName, TableRegistry.getGlobalInstance());
  }

  public Table<T> getTable() {
    return table;
  }

  protected TableQueryExecutor<T> getInstance() {
    return this;
  }

  public List<T> execute() {
    Query query = QueryGenerator.build(this, table.getColumns());
    try {
      table.open();
      return table.query(query);
    } finally {
      table.close();
    }
  }

  /**
   * Queries the Table with the given name and a value for the primary key.
   *
   * @param tableName : Name of the Table
   * @param primaryKeyValue : Value
   * @param <T> : DataType within the DataStore
   * @return : Returns the data T based on the query
   */
  public static <T> T queryByPrimaryKey(String tableName, Long primaryKeyValue) {
    TableQueryExecutor<T> executor = new TableQueryExecutor<T>(tableName);
    Column primaryKey = getPrimaryKeyColumn(executor.getTable().getColumns());
    return queryByUniqueColumn(executor, primaryKey, FilterValue.of(primaryKeyValue));
  }

  /**
   * Queries the Table with the given name, a unique column and a value for the column.
   *
   * @param tableName : Name of the Table
   * @param column : Unique column of the Table
   * @param uniqueColumnValue : Value
   * @param <T> : DataType within the DataStore
   * @return : Returns the data T based on the query
   */
  public static <T> T queryByUniqueColumn(String tableName, Column column, FilterValue<?> uniqueColumnValue) {
    TableQueryExecutor<T> executor = new TableQueryExecutor<T>(tableName);
    return queryByUniqueColumn(executor, column, uniqueColumnValue);
  }

  private static <T> T queryByUniqueColumn(TableQueryExecutor<T> executor, Column uniqueColumn,
      FilterValue<?> uniqueColumnValue) {
    List<T> data = executor.where(uniqueColumn).equals(uniqueColumnValue).execute();
    if (data.size() == 1) {
      return data.get(0);
    } else if (data.size() == 0) {
      return null;
    } else {
      throw new IllegalStateException("Too many rows returned for the query. Query on Column " + uniqueColumn.getName()
          + " returned " + data.size() + " rows");
    }
  }

  private static Column getPrimaryKeyColumn(List<Column> columnList) {
    for (Column column : columnList) {
      if (column.getSpec().getProperties() != null
          && column.getSpec().getProperties().contains(TableColumnProperty.PRIMARY_KEY)) {
        return column;
      }
    }
    throw new IllegalStateException("No Primary Key found for Table");
  }
}
