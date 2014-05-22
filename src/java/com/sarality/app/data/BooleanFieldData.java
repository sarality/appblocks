package com.sarality.app.data;

public class BooleanFieldData extends BaseFieldData<Boolean> implements FieldData<Boolean> {

  public BooleanFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.BOOLEAN, data);
  }

  public BooleanFieldData(Field field, Boolean value, BaseFieldBasedDataObject<?> data) {
    this(field, data);
    setValue(value);
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(Boolean.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((Boolean) value);    
  }

  @Override
  public String getStringValue() {
    if (!hasValue() || getValue() == null) {
      return null;
    }
    return getValue().toString();
  }  
}
