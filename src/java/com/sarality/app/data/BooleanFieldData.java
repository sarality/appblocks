package com.sarality.app.data;

public class BooleanFieldData extends BaseFieldData<Boolean> implements FieldData<Boolean> {

  public BooleanFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.BOOLEAN, data);
  }

  @Override
  public void parseFrom(String stringValue) {
    setValue(Boolean.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    setValue((Boolean) value);    
  }
}
