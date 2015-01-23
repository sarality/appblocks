package com.sarality.app.datastore.db;

import com.sarality.app.datastore.query.QueryBuilder;

/**
 * Class used to build Query to either query or update a Table.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TableQueryBuilder extends QueryBuilder {

  public TableQueryBuilder(Table<?> table) {
    super(table);
  }

  public TableQueryBuilder(String tableName, TableRegistry registry) {
    this(registry.getTable(tableName));
  }

  public Table<?> getTable() {
    return (Table<?>) super.getDataStore();
  }
}
