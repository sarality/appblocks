package com.sarality.app.datastore.query;

enum Operator {
  EQUALS("="),
  NOT_EQUALS("!="),
  LESS_THAN("<"),
  GREATER_THAN(">"),
  IS_NULL("IS NULL"),
  IS_NOT_NULL("IS NOT NULL"),
  AND("AND"),
  OR("OR"),
  NOT("!");
  
  private final String sqlString;
  
  private Operator(String sqlString) {
    this.sqlString = sqlString;
  }
  
  public String getSqlString() {
    return sqlString;
  }
}
