package com.sarality.app.datastore;


public enum ColumnFormat {
  // Store date as integer in format YYYYMMDDhhmmss
  DATE_AS_INT(ColumnDataType.INTEGER),
  // Store mapped value for enum
  ENUM_AS_INT(ColumnDataType.INTEGER),
  // Store mapped value for enum
  ENUM_AS_TXT(ColumnDataType.TEXT),
  // Store date as epoch i.e. milliseconds since January 1, 1970
  EPOCH(ColumnDataType.INTEGER);

  private ColumnDataType underlyingDataType;

  private ColumnFormat(ColumnDataType underlyingDataType) {
    this.underlyingDataType = underlyingDataType;
  }

  public ColumnDataType getUnderlyingDataType() {
    return underlyingDataType;
  }
}
