package com.sarality.app.data;

public class EnumFieldDataFactory<E extends Enum<E>> {

  private final Class<E> enumClass;
  
  public EnumFieldDataFactory(Class<E> enumClass) {
    this.enumClass = enumClass;
  }

  public FieldData<E> create(Field field, E value) {
    EnumFieldData<E> data = new EnumFieldData<E>(field, enumClass, null);
    data.setValue(value);
    return data;
  }
}
