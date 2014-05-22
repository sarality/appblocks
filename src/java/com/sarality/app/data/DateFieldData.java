package com.sarality.app.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFieldData extends BaseFieldData<Date> implements FieldData<Date> {

  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
      Locale.getDefault());
  
  public DateFieldData(Field field, BaseFieldBasedDataObject<?> data) {
    super(field, FieldData.Type.DATE, data);
  }

  public DateFieldData(Field field, Date value, BaseFieldBasedDataObject<?> data) {
    this(field, data);
    setValue(value);
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
}
