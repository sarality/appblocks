package com.sarality.app.data.field.test;

import com.sarality.app.data.field.Field;

/**
 * A simple implementation of Field used for testing purposes.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class TestField implements Field {

  private final DataType dataType;
  private final String name;

  public TestField(String name, DataType dataType) {
    this.name = name;
    this.dataType = dataType;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }
}
