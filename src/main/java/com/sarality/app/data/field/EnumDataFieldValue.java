package com.sarality.app.data.field;

import com.sarality.app.data.EnumData;
import com.sarality.app.data.EnumDataRegistry;
import com.sarality.app.data.field.Field.DataType;

public class EnumDataFieldValue<E extends EnumData<E>> extends BaseFieldValue<E> {

  private final EnumDataRegistry registry;

  protected EnumDataFieldValue(Field field, Class<E> valueClass, EnumDataRegistry registry) {
    super(field, DataType.ENUM_DATA, valueClass);
    this.registry = registry;
  }

  public EnumDataFieldValue(Field field, Class<E> valueClass) {
    this(field, valueClass, EnumDataRegistry.getGlobalInstance());
  }
  
  @Override
  public void castFrom(Object value) {
    @SuppressWarnings("unchecked")
    E enumValue = (E) value;
    setValue(enumValue);
  }

  @Override
  public void parseFrom(String stringValue) {
    E value = registry.valueOf(getValueClass(), stringValue);
    setValue(value);
  }

  @Override
  public String getStringValue() {
    return getValue().getEnumName();
  }

  public static class Factory<E extends EnumData<E>> implements FieldValueFactory<E> {
    private final Class<E> valueClass;
    private final EnumDataRegistry registry;

    public Factory(Class<E> valueClass, EnumDataRegistry registry) {
      this.valueClass = valueClass;
      this.registry = registry;
    }

    public Factory(Class<E> valueClass) {
      this(valueClass, EnumDataRegistry.getGlobalInstance());
    }

    @Override
    public FieldValue<E> createFieldValue(Field field) {
      return new EnumDataFieldValue<E>(field, valueClass, registry);
    }
  }
}
