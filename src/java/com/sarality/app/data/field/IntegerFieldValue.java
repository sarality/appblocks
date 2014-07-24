package com.sarality.app.data.field;

import com.sarality.app.data.field.Field.DataType;

/**
 * Stores a Field with a Integer Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
class IntegerFieldValue extends BaseFieldValue<Integer> {

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   */
  private IntegerFieldValue(Field field) {
    super(field, DataType.INTEGER, Integer.class);
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(Integer.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((Integer) value);
  }

  @Override
  public String getStringValue() {
    if (!hasValue()) {
      return null;
    }
    return Integer.toString(getValue());
  }

  static class Factory implements FieldValueFactory<Integer> {

    @Override
    public IntegerFieldValue createFieldValue(Field field) {
      return new IntegerFieldValue(field);
    }
  }
}
