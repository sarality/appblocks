package com.sarality.app.datastore.query;

import android.util.Pair;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.datastore.Column;

import java.util.List;

/**
 * Utility class that builds a Query given a QueryBuilder
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class QueryGenerator {

  /**
   * Builds the Query for the Query Builder.
   *
   * @return Query for the builder.
   */
  public static <B extends BaseQueryBuilder<B>> Query build(B builder, List<Column> columnList) {
    // Convert all filters to a Clause to make sure we are only dealing with clauses.
    builder.convertFiltersToClause();

    List<String> valueList = Lists.of();
    StringBuilder whereClauseBuilder = new StringBuilder();
    boolean isFirst = true;
    // Iterate through the clauses and add to where clause string and values.
    for (QueryClause clause : builder.getClauseList()) {
      if (!isFirst) {
        whereClauseBuilder.append(" ").append(builder.getFilterType().getSqlString());
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
    for (Pair<Column, Boolean> orderByColumn : builder.getOrderByColumns()) {
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

    return new Query(columnList, builder.getFilterType(), builder.getFilterList(), builder.getOrderByColumns(),
        whereClause,
        valueList,
        orderByClause.toString());
  }

}
