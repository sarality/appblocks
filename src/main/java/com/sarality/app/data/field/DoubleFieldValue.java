package com.sarality.app.data.field;

/**
 * Stores a Field with a Double Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DoubleFieldValue extends BaseFieldValue<Double> {

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   */
  public DoubleFieldValue(Field field) {
    super(field, Field.DataType.DOUBLE, Double.class);
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(Double.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((Double) value);
  }

  @Override
  public String getStringValue() {
    if (!hasValue() || getValue() == null) {
      return null;
    }
    return Double.toString(getValue());
  }

  static class Factory implements FieldValueFactory<Double> {

    @Override
    public FieldValue<Double> createFieldValue(Field field) {
      return new DoubleFieldValue(field);
    }
  }

}
