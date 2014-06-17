package com.sarality.app.datastore;

import java.util.List;

/**
 * Query to fetch data from a DataStore.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class Query {
  // List of columns of the datasource that will be returned from the query
  private final List<Column> columns;

  // Where args
  private final String whereClause;

  // where values
  private final List<String> whereColumnValueList;

  // ordering of the query result
  private final String orderBy;

  /**
   * Constructor
   * 
   * @param columns
   *          Specific columns to be returned
   * @param whereClause
   *          Query on specific columns
   * @param whereClauseValueList
   *          Query on specific columns with specific values
   */
  public Query(List<Column> columns, String whereClause, List<String> whereClauseValueList) {
    this.columns = columns;
    this.whereClause = whereClause;
    this.whereColumnValueList = whereClauseValueList;
    this.orderBy = null;
  }

  /**
   * Constructor
   * 
   * @param columns
   *          Specific columns to be returned
   * @param whereClause
   *          Query on specific columns
   * @param whereClauseValueList
   *          Query on specific columns with specific values
   * @param orderBy
   *          Return result in a specific order
   */
  public Query(List<Column> columns, String whereClause, List<String> whereClauseValueList, String orderBy) {
    this.columns = columns;
    this.whereClause = whereClause;
    this.whereColumnValueList = whereClauseValueList;
    this.orderBy = orderBy;
  }

  /**
   * Constructor Empty query
   */
  public Query() {
    this(null, null, null, null);
  }

  /**
   * Returns the list of columns
   * 
   * @return Return as String[] as required as sqldatabase
   */
  public String[] getColumns() {
    if (columns == null)
      return null;

    String[] columnString = new String[columns.size()];
    for (int i = 0; i < columns.size(); i++) {
      columnString[i] = columns.get(i).toString();
    }
    return columnString;
  }

  /**
   * Returns the store whereclause
   * 
   * @return
   */
  public String getWhereClause() {
    return whereClause;
  }

  /**
   * Returns the values for the whereclause
   * 
   * @return
   */
  public String[] getWhereClauseValues() {
    if (whereColumnValueList == null || whereColumnValueList.size() < 1) {
      return null;
    }
    return whereColumnValueList.toArray(new String[whereColumnValueList.size()]);
  }

  /**
   * Returns the orderBy value
   * 
   * @return
   */
  public String getOrderBy() {
    return orderBy;
  }
}
