package com.sarality.app.datastore.query;

import android.util.Pair;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * A query builder that supports complex nested clauses.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CompoundQueryBuilder extends BaseQueryBuilder<CompoundQueryBuilder> {

  private final List<Column> columnList;

  // Indicates in what order should the rows be returned
  private final List<Pair<Column, Boolean>> orderByColumnList = new ArrayList<Pair<Column, Boolean>>();

  public CompoundQueryBuilder(List<Column> columnList) {
    this.columnList = columnList;
  }

  /**
   * Builds the query to allow rows to be returned in a order defined by a specific column
   *
   * @param column Column to define the filter on.
   * @param ascendingOrder Indicates whether data needs to sorted on ascending order of column value.
   * @return The current QueryBuilder
   */
  public CompoundQueryBuilder orderBy(Column column, boolean ascendingOrder) {
    orderByColumnList.add(new Pair<Column, Boolean>(column, ascendingOrder));
    return getInstance();
  }

  /**
   * Builds the Query for the Query Builder.
   *
   * @return Query for the builder.
   */
  public Query build() {
    // Convert all filters to a Clause to make sure we are only dealing with clauses.
    convertFiltersToClause();

    List<String> valueList = Lists.of();
    StringBuilder whereClauseBuilder = new StringBuilder();
    boolean isFirst = true;
    // Iterate through the clauses and add to where clause string and values.
    for (QueryClause clause : getClauseList()) {
      if (!isFirst) {
        whereClauseBuilder.append(" ").append(getFilterType().getSqlString());
      }
      clause.appendWhereClauseAndValues(whereClauseBuilder, valueList);
      isFirst = false;
    }

    // If there are no values or where clause is empty, set values to null.
    if (valueList.isEmpty()) {
      valueList = null;
    }
    String whereClause = whereClauseBuilder.toString();
    if (whereClause.length() == 0) {
      whereClause = null;
    }

    // Process order by columns.
    StringBuilder orderByClause = new StringBuilder();
    boolean processedFirstColumn = false;
    for (Pair<Column, Boolean> orderByColumn : orderByColumnList) {
      if (processedFirstColumn) {
        orderByClause.append(", ");
      }
      orderByClause.append(orderByColumn.first.getName()).append(" ");
      if (orderByColumn.second) {
        orderByClause.append(" ASC");
      } else {
        orderByClause.append(" DESC");
      }
      processedFirstColumn = true;
    } // Order By Column

    return new Query(columnList, getFilterType(), getFilterList(), orderByColumnList, whereClause, valueList,
        orderByClause.toString());
  }

  @Override
  protected CompoundQueryBuilder getInstance() {
    return this;
  }
}
