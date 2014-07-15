package com.sarality.app.data.field;

/**
 * Stores a Field with an Enum Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumFieldValue<E extends Enum<E>> extends BaseFieldValue<E> {

  private final Class<E> enumClass;

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   * @param enumClass The type of Enum that is stored in the FieldValue.
   */
  public EnumFieldValue(Field field, Class<E> enumClass) {
    super(field, Field.DataType.ENUM, enumClass);
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

  static class Factory<E extends Enum<E>> implements FieldValueFactory<E> {

    private final Class<E> enumClass;

    public Factory(Class<E> enumClass) {
      this.enumClass = enumClass;
    }

    @Override
    public FieldValue<E> createFieldValue(Field field) {
      return new EnumFieldValue<E>(field, enumClass);
    }
  }

}
