package com.sarality.app.datastore.query;

import java.util.List;

import android.util.Pair;

import com.sarality.app.datastore.Column;

/**
 * Query to fetch data from a DataStore.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class Query {
  // List of columns of the Data Source that will be returned from the query
  private final List<Column> columns;

  // The type of filter defined on this query. Indicates whether there are filter conditions
  // and whether all (AND) or one (OR) of these conditions must be true for the filter to pass.
  private final FilterType filterType;
  // List of filters for the query
  private final List<QueryFilter> filterList;
  // List of filters for the query
  private final List<Pair<Column, Boolean>> orderByColumnList;

  // Where clause for the query
  private final String whereClause;
  // Values to be used for parameters of where clause.
  private final List<String> whereClauseValueList;

  // Order by clause for the query
  private final String orderByClause;

  /**
   * Constructor.
   * 
   * @param columns Specific columns to be returned
   * @param filterList List of QueryFilters that needed to applied on the DataSource.
   * @param orderByColumnList The List of Columns to sort the data by.
   * @param whereClause SQL Where clause for the query
   * @param whereClauseValueList List of String values for the parameters in the Where clause.
   * @param orderByClause Order by Clause as a String.
   */
  protected Query(List<Column> columns, FilterType filterType, 
      List<QueryFilter> filterList, List<Pair<Column, Boolean>> orderByColumnList, 
      String whereClause, List<String> whereClauseValueList, String orderByClause) {
    this.columns = columns;
    this.filterType = filterType;
    this.filterList = filterList;
    this.orderByColumnList = orderByColumnList;
    this.whereClause = whereClause;
    this.whereClauseValueList = whereClauseValueList;
    this.orderByClause = orderByClause;
  }

  /**
   * Returns the list of columns
   * 
   * @return Return as String[] as required by SQL Database
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
   * @return The Type of Filter defined by the Query i.e. AND, OR or NONE
   */
  public FilterType getFilterType() {
    return filterType;
  }

  /**
   * @return List of Filters that that need to evaluated for the Query.
   */
  public List<QueryFilter> getFilterList() {
    return filterList;
  }

  /**
   * @return List of columns to order the results by and whether the sorting needs to be done is ascending or 
   *         descending order.
   */
  public List<Pair<Column, Boolean>> getOrderByColumnList() {
    return orderByColumnList;
  }

  /**
   * @return The where clause for the query.
   */
  public String getWhereClause() {
    return whereClause;
  }

  /**
   * @return Values for the parameters in the where clause.
   */
  public String[] getWhereClauseValues() {
    if (whereClauseValueList == null || whereClauseValueList.size() < 1) {
      return null;
    }
    return whereClauseValueList.toArray(new String[whereClauseValueList.size()]);
  }

  /**
   * @return The order by clause.
   */
  public String getOrderBy() {
    return orderByClause;
  }
}
