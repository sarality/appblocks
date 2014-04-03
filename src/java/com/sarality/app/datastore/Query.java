package com.sarality.app.datastore;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.datastore.db.Table;

/**
 * Query to fetch data from a database table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class Query {
  private final List<Column> whereColumnList = new ArrayList<Column>();
  private final List<String> whereColumnValueList = new ArrayList<String>();

  private final List<Column> columns;

  public Query(List<Column> columns) {
    this.columns = columns;
  }

  public Query(Column... columns) {
    this(ColumnUtils.getAllColumns(columns));
  }

  public Query(Table<?> table) {
    this(table.getColumns());
  }

  public Query withFilter(Column column, String value) {
    whereColumnList.add(column);
    whereColumnValueList.add(value);
    return this;
  }

  public List<Column> getColumns() {
    return columns;
  }
}
