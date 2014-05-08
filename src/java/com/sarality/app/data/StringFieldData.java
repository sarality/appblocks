package com.sarality.app.data;

public class StringFieldData extends BaseFieldData<String> implements FieldData<String> {

  public StringFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.STRING, data);
  }

  @Override
  public void parseFrom(String stringValue) {
    setValue(stringValue);
  }

  @Override
  public void castFrom(Object value) {
    setValue((String) value);
  }
}
