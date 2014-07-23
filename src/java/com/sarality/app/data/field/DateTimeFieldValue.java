package com.sarality.app.data.field;

import hirondelle.date4j.DateTime;

import java.util.Locale;

/**
 * Stores a Field with a DateTime Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
class DateTimeFieldValue extends BaseFieldValue<DateTime> {

  private final String dateTimeFormat;

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   */
  private DateTimeFieldValue(Field field, Field.DataType dataType, String dateTimeFormat) {
    super(field, dataType, DateTime.class);
    this.dateTimeFormat = dateTimeFormat;
  }

  @Override
  public void parseFrom(String stringValue) {
    if (stringValue == null) {
      setValue(null);
      return;
    }
    if (DateTime.isParseable(stringValue)) {
      setValue(new DateTime(stringValue));
    }
  }

  @Override
  public void castFrom(Object value) {
    if (value == null) {
      setValue(null);
      return;
    }
    setValue((DateTime) value);
  }

  @Override
  public String getStringValue() {
    if (!hasValue() || getValue() == null) {
      return null;
    }
    return getValue().format(dateTimeFormat, Locale.getDefault());
  }

  /**
   * Factory for Field Values that have both Date and Time.
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public static class Factory implements FieldValueFactory<DateTime> {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public FieldValue<DateTime> createFieldValue(Field field) {
      return new DateTimeFieldValue(field, Field.DataType.DATETIME, DATE_TIME_FORMAT);
    }
  }

  /**
   * Factory for Field Values that have only Time with no Date.
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public static class TimeOnlyFactory implements FieldValueFactory<DateTime> {

    private static final String TIME_FORMAT = "HH:mm:ss";

    @Override
    public FieldValue<DateTime> createFieldValue(Field field) {
      return new DateTimeFieldValue(field, Field.DataType.TIME_ONLY, TIME_FORMAT);
    }
  }

  /**
   * Factory for Field Values that have only Date with no Time.
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  static class DateOnlyFactory implements FieldValueFactory<DateTime> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public FieldValue<DateTime> createFieldValue(Field field) {
      return new DateTimeFieldValue(field, Field.DataType.DATE_ONLY, DATE_FORMAT);
    }
  }
}
