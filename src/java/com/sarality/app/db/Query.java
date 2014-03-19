package com.sarality.app.db;

import java.util.ArrayList;
import java.util.List;

/**
 * Query to fetch data from a database table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class Query {
  private final List<Column> whereColumnList = new ArrayList<Column>();
  private final List<String> whereColumnValueList = new ArrayList<String>();

  public Query withWhereColumn(Column column, String value) {
    whereColumnList.add(column);
    whereColumnValueList.add(value);
    return this;
  }
}
