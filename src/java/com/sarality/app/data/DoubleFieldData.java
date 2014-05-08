package com.sarality.app.data;

public class DoubleFieldData extends BaseFieldData<Double> implements FieldData<Double> {

  public DoubleFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.DOUBLE, data);
  }

  @Override
  public void parseFrom(String stringValue) {
    setValue(Double.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    setValue((Double) value);    
  }
}
