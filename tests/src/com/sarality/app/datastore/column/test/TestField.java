package com.sarality.app.datastore.column.test;

import com.sarality.app.data.field.Field;

/**
 * An enumeration of Fields that is used for testing purposes.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public enum TestField implements Field {
  BOOLEAN_FIELD(DataType.BOOLEAN),
  DATE_FIELD(DataType.DATE),
  DATE_TIME_FIELD(DataType.DATETIME),
  DOUBLE_FIELD(DataType.DOUBLE),
  ENUM_FIELD(DataType.ENUM),
  ENUM_DATA_FIELD(DataType.ENUM_DATA),
  INTEGER_FIELD(DataType.INTEGER),
  LONG_FIELD(DataType.LONG),
  STRING_FIELD(DataType.STRING);

  private final DataType dataType;

  private TestField(DataType dataType) {
    this.dataType = dataType;
  }

  @Override
  public String getName() {
    return name();
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
