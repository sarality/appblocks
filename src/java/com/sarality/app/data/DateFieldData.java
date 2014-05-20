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

  @Override
  public void parseFrom(String stringValue) {
    try {
      setValue(DATE_FORMAT.parse(stringValue));
    } catch (ParseException e) {
      // All dates must be provided in the format provided above
    }
  }

  @Override
  public void castFrom(Object value) {
    setValue((Date) value);
  }
}
