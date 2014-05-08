package com.sarality.app.data;

public class LongFieldData extends BaseFieldData<Long> implements FieldData<Long> {

  public LongFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.LONG, data);
  }

  @Override
  public void parseFrom(String stringValue) {
    setValue(Long.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    setValue((Long) value);    
  }
}
