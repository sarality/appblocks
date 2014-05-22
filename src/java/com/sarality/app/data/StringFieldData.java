package com.sarality.app.data;

public class StringFieldData extends BaseFieldData<String> implements FieldData<String> {

  public StringFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.STRING, data);
  }

  public StringFieldData(Field field, String value, BaseFieldBasedDataObject<?> data) {
    this(field, data);
    setValue(value);
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(stringValue);
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((String) value);
  }

  @Override
  public String getStringValue() {
    if (!hasValue()) {
      return null;
    }
    return getValue();
  }
}
