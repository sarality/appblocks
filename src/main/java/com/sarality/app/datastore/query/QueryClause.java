package com.sarality.app.datastore.query;

import java.util.List;

/**
 * A query clause that contains filters defined by the query as well as sub clauses.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class QueryClause {

  // The filters that form the Where clause i.e. Column and Value pair
  private final List<QueryFilter> filterList;
  // The sub clauses that are part of this where clause e.g. In the where clause (A = 7) && ((B = 8) || (C = 9)),
  // (B = 8 || C = 9) is a sub clause.
  private final List<QueryClause> clauseList;
  // Type of filter - AND vs OR
  private final FilterType filterType;

  QueryClause(List<QueryFilter> filterList, List<QueryClause> clauseList, FilterType filterType) {
    this.filterList = filterList;
    this.clauseList = clauseList;
    this.filterType = filterType;
  }

  /**
   * Adds to the where clause query string and associated values.
   *
   * @param whereClauseBuilder Where Clause string that will be appended to.
   * @param valueList values for the where clause string that will be appended to.
   */
  void appendWhereClauseAndValues(StringBuilder whereClauseBuilder, List<String> valueList) {
    // Start each clause with a parentheses.
    whereClauseBuilder.append("(");

    boolean isFirst = true;
    // Add all filters first, which also means that there are no sub clauses in this clause.
    for (QueryFilter filter : filterList) {
      if (!isFirst) {
        // The AND and OR should be skipped for the first Filter or Clause
        whereClauseBuilder.append(" ").append(filterType.getSqlString());
      }

      whereClauseBuilder.append(" (").append(filter.getColumn().getName()).append(" ")
          .append(filter.getOperation().getSqlString());
      if (filter.getOperation() == Operator.IN) {
        appendWhereClauseAndValuesForInFilter(filter, whereClauseBuilder, valueList);
      } else {
        if (filter.getValue() != null) {
          String value = filter.getValue().getStringValue(filter.getColumn());
          whereClauseBuilder.append(" ? ");
          if (filter.getOperation().equals(Operator.LIKE)) {
            value = "%" + value + "%";
          }
          valueList.add(value);
        }
      }
      whereClauseBuilder.append(")");
      isFirst = false;
    }

    // Process all sub clauses here.
    for (QueryClause clause : clauseList) {
      if (!isFirst) {
        whereClauseBuilder.append(" ").append(filterType.getSqlString());
      }
      clause.appendWhereClauseAndValues(whereClauseBuilder, valueList);
      isFirst = false;
    }

    // End each clause with a parentheses.
    whereClauseBuilder.append(")");
  }

  /**
   * Appends to where clause string and values for an IN filter.
   *
   * @param filter QueryFilter IN Filter to append where clause and values for.
   * @param whereClauseBuilder Where Clause string that will be appended to.
   * @param valueList values for the where clause string that will be appended to.
   */
  private void appendWhereClauseAndValuesForInFilter(QueryFilter filter, StringBuilder whereClauseBuilder,
      List<String> valueList) {
    List<FilterValue<?>> values = filter.getValues();
    boolean isFirst = true;

    for (FilterValue<?> value : values) {
      // Add a comma for everything but the first value
      if (!isFirst) {
        whereClauseBuilder.append(",");
      }
      isFirst = false;

      // Add a ? in the where clause and the value in value list.
      String stringValue = value.getStringValue(filter.getColumn());
      valueList.add(stringValue);
      whereClauseBuilder.append("?");
    }
    whereClauseBuilder.append(")");
  }
}
