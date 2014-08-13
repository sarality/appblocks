package com.sarality.app.data.field;

/**
 * Stores a Field with a String Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class StringFieldValue extends BaseFieldValue<String> {

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   */
  public StringFieldValue(Field field) {
    super(field, Field.DataType.STRING, String.class);
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(stringValue);
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((String) value);
  }

  @Override
  public String getStringValue() {
    if (!hasValue()) {
      return null;
    }
    return getValue();
  }

  public static class Factory implements FieldValueFactory<String> {

    @Override
    public FieldValue<String> createFieldValue(Field field) {
      return new StringFieldValue(field);
    }    
  }
}
