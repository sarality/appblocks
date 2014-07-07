package com.sarality.app.datastore.query;

/**
 * Type of filter that is defined for the query.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public enum FilterType {
  AND("AND"), // There are one or more conditions and all them must be true for the filter to pass. 
  OR("OR"), // There are multiple conditions and any one of them must be true for the filter to pass.
  NONE(""); // No conditions are defined. All entries pass the filter.

  private final String sqlString;

  private FilterType(String sqlString) {
    this.sqlString = sqlString;
  }

  public String getSqlString() {
    return sqlString;
  }  
}
