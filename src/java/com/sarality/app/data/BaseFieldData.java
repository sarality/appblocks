package com.sarality.app.data;

abstract class BaseFieldData<T> implements FieldData<T> {

  private T value = null;
  private boolean hasValue = false;
  private final Field field;
  private final FieldData.Type fieldType;

  protected BaseFieldData(Field field, FieldData.Type fieldType, BaseFieldBasedDataObject<?> data) {
    this.field = field;
    this.fieldType = fieldType;
    if (data != null) {
      data.registerField(this);
    }
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
    if (!hasValue) {
      return null;
    }
    return value;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
    this.hasValue = true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(field.getName()).append(" : ").append(getStringValue());
    return builder.toString();
  }
}
