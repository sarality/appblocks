package com.sarality.app.data;

public class LongFieldData extends BaseFieldData<Long> implements FieldData<Long> {

  public LongFieldData(Field field) {
    super(field, FieldData.Type.LONG);
  }

  @Override
  public void parseFrom(String stringValue) {
    setValue(Long.getLong(stringValue));
  }
}
