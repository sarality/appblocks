package com.sarality.app.data;

public class DoubleFieldData extends BaseFieldData<Double> implements FieldData<Double> {

  public DoubleFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.DOUBLE, data);
  }

  public DoubleFieldData(Field field, Double value, BaseFieldBasedDataObject<?> data) {
    this(field, data);
    setValue(value);
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(Double.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((Double) value);    
  }

  @Override
  public String getStringValue() {
    if (!hasValue() || getValue() == null) {
      return null;
    }
    return Double.toString(getValue());
  }    
}
