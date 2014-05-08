package com.sarality.app.data;

public class EnumFieldData<E extends Enum<E>> extends BaseFieldData<E> implements FieldData<E>{

  private final Class<E> enumClass;
  
  public EnumFieldData(Field field, Class<E> enumClass, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.ENUM, data);
    this.enumClass = enumClass;
  }
  
  @Override
  public void parseFrom(String stringValue) {
    setValue(Enum.valueOf(enumClass, stringValue));
  }

  @Override
  public void castFrom(Object value) {
    @SuppressWarnings("unchecked")
    E enumValue = (E) value;
    setValue(enumValue);
  }
}
