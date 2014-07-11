package com.sarality.app.data.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Stores a Field with a Date Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
class DateFieldValue extends BaseFieldValue<Date> {

  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   */
  private DateFieldValue(Field field) {
    super(field, Field.DataType.DATE, Date.class);
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    try {
      setValue(DATE_FORMAT.parse(stringValue));
    } catch (ParseException e) {
      // All dates must be provided in the format provided above
    }
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((Date) value);
  }

  @Override
  public String getStringValue() {
    if (!hasValue() || getValue() == null) {
      return null;
    }
    return DATE_FORMAT.format(getValue());
  }

  static class Factory implements FieldValueFactory {

    @Override
    public FieldValue<Date> createFieldValue(Field field) {
      return new DateFieldValue(field);
    }
  }

}
