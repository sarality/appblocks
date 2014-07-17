package com.sarality.app.data.field;

import com.sarality.app.data.EnumData;
import com.sarality.app.data.EnumDataRegistry;
import com.sarality.app.data.field.Field.DataType;

public class EnumDataFieldValue<E extends EnumData<E>> extends BaseFieldValue<E> {


  public EnumDataFieldValue(Field field, DataType fieldType, Class<E> valueClass) {
    super(field, DataType.ENUM, valueClass);
  }

  @Override
  public void castFrom(Object value) {
    @SuppressWarnings("unchecked")
    E enumValue = (E) value;
    setValue(enumValue);
  }

  @Override
  public void parseFrom(String stringValue) {
    E value = EnumDataRegistry.valueOf(getValueClass(), stringValue);
    setValue(value);
  }

  @Override
  public String getStringValue() {
    return getValue().getEnumName();
  }

  public static class Factory<E extends EnumData<E>> implements FieldValueFactory<E> { 
    private final Class<E> valueClass;
    
    public Factory(Class<E> valueClass) {
      this.valueClass = valueClass;
    }

    @Override
    public FieldValue<E> createFieldValue(Field field) {
      return new EnumDataFieldValue<E>(field, DataType.ENUM, valueClass);
    }
  }
}
