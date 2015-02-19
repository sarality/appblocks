package com.sarality.app.datastore.db;

import com.sarality.app.datastore.query.BaseQueryBuilder;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.datastore.query.QueryGenerator;

/**
 * Class used to build Query to either query or update a Table.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TableQueryBuilder<T> extends BaseQueryBuilder<TableQueryBuilder<T>> {

  private final Table<T> table;

  public TableQueryBuilder(Table<T> table) {
    this.table = table;
  }

  @SuppressWarnings("unchecked")
  public TableQueryBuilder(String tableName, TableRegistry registry) {
    this((Table<T>) registry.getTable(tableName));
  }

  public TableQueryBuilder(String tableName) {
    this(tableName, TableRegistry.getGlobalInstance());
  }

  @Override
  protected TableQueryBuilder<T> getInstance() {
    return this;
  }

  public Table<T> getTable() {
    return table;
  }

  /**
   * Builds the Query for the Query Builder.
   *
   * @return Query for the builder.
   */
  public Query build() {
    return QueryGenerator.build(this, table.getColumns());
  }
}
