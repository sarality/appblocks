package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;

import java.util.List;

/**
 * Represents a filter for a Query to restrict the data returned by a query on a data store.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class QueryFilter {
  private final Column column;
  private final Operator operation;
  private final ValueCardinality valueCardinality;
  private final FilterValue<?> value;
  private final List<FilterValue<?>> valueList;

  public enum ValueCardinality {
    NO_VALUE,
    SINGLE_VALUE,
    MULTI_VALUE;
  }

  public QueryFilter(Column column, Operator operation) {
    this.column = column;
    this.operation = operation;
    this.value = null;
    this.valueList = null;
    this.valueCardinality = ValueCardinality.NO_VALUE;
  }

  public QueryFilter(Column column, Operator operation, FilterValue<?> value) {
    this.column = column;
    this.operation = operation;
    this.value = value;
    this.valueList = null;
    this.valueCardinality = ValueCardinality.SINGLE_VALUE;
  }

  public QueryFilter(Column column, Operator operation, List<FilterValue<?>> valueList) {
    this.column = column;
    this.operation = operation;
    this.valueList = valueList;
    this.value = null;
    this.valueCardinality = ValueCardinality.MULTI_VALUE;
  }

  /**
   * @return Column that this is a condition / filter on
   */
  public final Column getColumn() {
    return column;
  }

  /**
   * @return Conditional / Comparison operation to run on the column.
   */
  public final Operator getOperation() {
    return operation;
  }

  /**
   * @return Value for the condition / comparison operation.
   */
  public final FilterValue<?> getValue() {
    return value;
  }

  /**
   * @return List of values for the condition / comparison operation.
   */
  public final List<FilterValue<?>> getValues() {
    return valueList;
  }

  /**
   * @return The Type of value used by the condition.
   */
  public final ValueCardinality getValueCardinality() {
    return valueCardinality;
  }
}
