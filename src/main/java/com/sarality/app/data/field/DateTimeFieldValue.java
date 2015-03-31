package com.sarality.app.data.field;

import hirondelle.date4j.DateTime;

import java.util.Locale;

/**
 * Stores a Field with a DateTime Value.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DateTimeFieldValue extends BaseFieldValue<DateTime> {

  private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final String TIME_FORMAT = "HH:mm:ss";

  public enum Format {
    DATE_TIME(DATE_TIME_FORMAT, Field.DataType.DATETIME),
    DATE_ONLY(DATE_FORMAT, Field.DataType.DATE_ONLY),
    TIME_ONLY(TIME_FORMAT, Field.DataType.TIME_ONLY);

    private String dateTimeFormat;
    private Field.DataType dataType;

    private Format(String dateTimeFormat, Field.DataType dataType) {
      this.dateTimeFormat = dateTimeFormat;
      this.dataType = dataType;
    }

    private String getFormatString() {
      return dateTimeFormat;
    }

    private Field.DataType getDataType() {
      return dataType;
    }
  }

  private final Format format;

  /**
   * Constructor.
   * 
   * @param field Field that this is a value for.
   */
  public DateTimeFieldValue(Field field, Format format) {
    super(field, format.getDataType(), DateTime.class);
    this.format = format;
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
    return getValue().format(format.getFormatString(), Locale.getDefault());
  }

  /**
   * Factory for Field Values that have both Date and Time.
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public static class Factory implements FieldValueFactory<DateTime> {

    @Override
    public FieldValue<DateTime> createFieldValue(Field field) {
      return new DateTimeFieldValue(field, Format.DATE_TIME);
    }
  }

  /**
   * Factory for Field Values that have only Time with no Date.
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public static class TimeOnlyFactory implements FieldValueFactory<DateTime> {

    @Override
    public FieldValue<DateTime> createFieldValue(Field field) {
      return new DateTimeFieldValue(field, Format.TIME_ONLY);
    }
  }

  /**
   * Factory for Field Values that have only Date with no Time.
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public static class DateOnlyFactory implements FieldValueFactory<DateTime> {

    @Override
    public FieldValue<DateTime> createFieldValue(Field field) {
      return new DateTimeFieldValue(field, Format.DATE_ONLY);
    }
  }
}
