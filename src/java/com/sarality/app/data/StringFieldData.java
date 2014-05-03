package com.sarality.app.data;

public class StringFieldData extends BaseFieldData<String> implements FieldData<String> {

  public StringFieldData(Field field) {
    super(field, FieldData.Type.STRING);
  }

  @Override
  public void parseFrom(String stringValue) {
    setValue(stringValue);
  }
}
