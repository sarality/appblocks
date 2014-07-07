package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;

/**
 * Represents a filter for a Query to restrict the data returned by a query on a data store.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> Type of value that we a filtering on.
 */
public class QueryFilter {
  private Column column;
  private Operator operation;
  private final FilterValue<?> value;

  public QueryFilter(Column column, Operator operation, FilterValue<?> value) {
    this.column = column;
    this.operation = operation;
    this.value = value;
  }

  /**
   * @return Column that this is a condition / filter on
   */
  public Column getColumn() {
    return column;
  }

  /**
   * @return Conditional / Comparison operation to run on the column.
   */
  public Operator getOperation() {
    return operation;
  }

  /**
   * @return Value for the condition / comparison operation.
   */
  public FilterValue<?> getValue() {
    return value;
  }
}
