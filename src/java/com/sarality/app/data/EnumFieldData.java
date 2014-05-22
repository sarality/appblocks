package com.sarality.app.data;

public class EnumFieldData<E extends Enum<E>> extends BaseFieldData<E> implements FieldData<E>{

  private final Class<E> enumClass;
  
  public EnumFieldData(Field field, Class<E> enumClass, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.ENUM, data);
    this.enumClass = enumClass;
  }
  
  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(Enum.valueOf(enumClass, stringValue));
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    @SuppressWarnings("unchecked")
    E enumValue = (E) value;
    setValue(enumValue);
  }

  @Override
  public String getStringValue() {
    if (!hasValue()) {
      return null;
    }
    return getValue().name();
  }      
}
