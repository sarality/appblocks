package com.sarality.app.data.field;

import com.sarality.app.data.field.Field.DataType;

/**
 * Stores a Field with a Long Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
class LongFieldValue extends BaseFieldValue<Long> {

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   */
  private LongFieldValue(Field field) {
    super(field, DataType.LONG, Long.class);
  }
  
  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    setValue(Long.valueOf(stringValue));
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((Long) value);    
  }

  @Override
  public String getStringValue() {
    if (!hasValue()) {
      return null;
    }
    return Long.toString(getValue());
  }

  static class Factory implements FieldValueFactory {

    @Override
    public LongFieldValue createFieldValue(Field field) {
      return new LongFieldValue(field);
    }    
  }
}
