package com.sarality.app.datastore;

import java.util.List;

/**
 * Query to fetch data from a DataStore.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class Query {
  private final List<Column> columns;
  private final String whereClause;
  private final List<String> whereColumnValueList;

  public Query(List<Column> columns, String whereClause, List<String> whereClauseValueList) {
    this.columns = columns;
    this.whereClause = whereClause;
    this.whereColumnValueList = whereClauseValueList;
  }

  public Query() {
    this(null, null, null);
  }
  
  public List<Column> getColumns() {
    return columns;
  }

  public String getWhereClause() {
    return whereClause;
  }

  public String[] getWhereClauseValues() {
    if (whereColumnValueList == null || whereColumnValueList.size() < 1) {
      return null;
    }
    return whereColumnValueList.toArray(new String[whereColumnValueList.size()]);
  }
}
