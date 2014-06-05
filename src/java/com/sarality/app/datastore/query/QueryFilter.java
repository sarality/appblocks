package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;

/**
 * Represents a filter for a Query to restrict the data returned
 * by a query on a data store.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> data type of the object 
 */
public class QueryFilter {
  private Column column;
  private Operator operation;
  private final String value;

  public QueryFilter(Column column, Operator operation, String value) {
    this.column = column;
    this.operation = operation;
    this.value = value;
  }

  public Column getColumn() {
    return column;
  }

  public Operator getOperation() {
    return operation;
  }

  public String getValue() {
    return value;
  }
}
