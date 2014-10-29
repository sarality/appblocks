package com.sarality.app.datastore.query;

/**
 * Operator in a Where Clause of a {@link Query}.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public enum Operator {
  EQUALS("="),
  NOT_EQUALS("!="),
  LESS_THAN("<"),
  GREATER_THAN(">"),
  IS_NULL("IS NULL"),
  IS_NOT_NULL("IS NOT NULL"),
  LIKE("like");

  private final String sqlString;

  private Operator(String sqlString) {
    this.sqlString = sqlString;
  }

  public String getSqlString() {
    return sqlString;
  }
}
