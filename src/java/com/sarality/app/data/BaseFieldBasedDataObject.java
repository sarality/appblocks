package com.sarality.app.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseFieldBasedDataObject<T> implements FieldBasedDataObject<T> {

  private final Map<Field, FieldData<?>> fieldDataMap = new HashMap<Field, FieldData<?>>();

  @Override
  public Set<Field> getFields() {
    return fieldDataMap.keySet();
  }

  protected void register(FieldData<?> fieldData) {
    fieldDataMap.put(fieldData.getField(), fieldData);
  }

  @Override
  public Object getValue(Field field) {
    FieldData<?> fieldData = fieldDataMap.get(field);
    if (fieldData != null && fieldData.hasValue()) {
      return fieldData.getValue();
    }
    return null;
  }

  
  public static abstract class Builder<T extends BaseFieldBasedDataObject<T>> implements FieldBasedDataObject.Builder<T> {
    private T data;
    
    public Builder() {
      this.data = newDataObject();
    }

    protected abstract T newDataObject();
    
    @Override
    public void setFieldValue(Field field, String value) {
      data.fieldDataMap.get(field);
    }

    @Override
    public T build() {
      return data;
    }
  }
}
