package com.sarality.app.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseFieldBasedDataObject<T> implements FieldBasedDataObject<T> {

  private final Map<Field, FieldData<?>> fieldDataMap = new LinkedHashMap<Field, FieldData<?>>();

  public BaseFieldBasedDataObject() {
    super();
  }

  @Override
  public Set<Field> getFields() {
    return fieldDataMap.keySet();
  }

  @Override
  public FieldData<?> getFieldData(Field field) {
    return fieldDataMap.get(field);
  }

  protected void registerField(FieldData<?> fieldData) {
    fieldDataMap.put(fieldData.getField(), fieldData);
  }
  
  protected void registerField(FieldData<?>... fieldDataArray) {
    for (FieldData<?> fieldData : fieldDataArray) {
      registerField(fieldData);
    }
  }

  @Override
  public Object getValue(Field field) {
    FieldData<?> fieldData = getFieldData(field);
    if (fieldData != null && fieldData.hasValue()) {
      return fieldData.getValue();
    }
    return null;
  }
  
  @Override
  public boolean hasValue(Field field) {
    FieldData<?> fieldData = getFieldData(field);
    return fieldData != null && fieldData.hasValue();
  }

  @Override
  public FieldBasedDataObject.Builder<T> getBuilder() {
    FieldBasedDataObject.Builder<T> builder = newBuilder();
    for (Field field : getFields()) {
      if (hasValue(field)) {
        builder.setFieldValue(field, getFieldData(field).getValue());
      }
    }
    return builder;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (Field field : getFields()) {
      builder.append(getFieldData(field).toString()).append("\n");
    }
    return builder.toString();    
  }
  
  public static abstract class Builder<T extends BaseFieldBasedDataObject<T>> implements FieldBasedDataObject.Builder<T> {
    protected final T data = newDataObject();

    public Builder() {
      super();
    }

    protected abstract T newDataObject();

    @Override
    public void setFieldValue(Field field, Object value) {
      data.getFieldData(field).castFrom(value);
    }

    @Override
    public void parseFieldValue(Field field, String value) {
      data.fieldDataMap.get(field).parseFrom(value);
    }
    
    @Override
    public T build() {
      return data;
    }
  }
}
