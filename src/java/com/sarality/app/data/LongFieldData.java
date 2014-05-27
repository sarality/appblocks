package com.sarality.app.data;

public class LongFieldData extends BaseFieldData<Long> implements FieldData<Long> {

  public LongFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.LONG, data);
  }

  public LongFieldData(Field field, Long value, BaseFieldBasedDataObject<?> data) {
    this(field, data);
    setValue(value);
  }
  
  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(Long.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((Long) value);    
  }

  @Override
  public String getStringValue() {
    if (!hasValue()) {
      return null;
    }
    return Long.toString(getValue());
  }    
}
