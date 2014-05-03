package com.sarality.app.data;

public abstract class BaseFieldData<T> implements FieldData<T> {

  private T value = null;
  private boolean hasValue = false;
  private final Field field;
  private final FieldData.Type fieldType;
  
  public BaseFieldData(Field field, FieldData.Type fieldType) {
    this.field = field;
    this.fieldType = fieldType;
  }

  @Override
  public Field getField() {
    return field;
  }

  @Override
  public FieldData.Type getType() {
    return fieldType;
  }

  @Override
  public boolean hasValue() {
    return hasValue;
  }

  @Override
  public T getValue() {
    return value;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
    this.hasValue = true;
  }
}
