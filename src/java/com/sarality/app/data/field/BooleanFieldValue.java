package com.sarality.app.data.field;

/**
 * Stores a Field with a Boolean Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
class BooleanFieldValue extends BaseFieldValue<Boolean> {

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   */
  private BooleanFieldValue(Field field) {
    super(field, Field.DataType.BOOLEAN, Boolean.class);
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(Boolean.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((Boolean) value);
  }

  @Override
  public String getStringValue() {
    if (!hasValue() || getValue() == null) {
      return null;
    }
    return getValue().toString();
  }

  static class Factory implements FieldValueFactory<Boolean> {

    @Override
    public FieldValue<Boolean> createFieldValue(Field field) {
      return new BooleanFieldValue(field);
    }
  }
}
