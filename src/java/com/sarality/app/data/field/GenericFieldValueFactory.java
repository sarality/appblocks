package com.sarality.app.data.field;

import hirondelle.date4j.DateTime;

import java.util.Date;

import com.sarality.app.data.EnumData;
import com.sarality.app.data.field.DateTimeFieldValue.Format;

/**
 * A utility factory class to make it easier to create FieldValues of a given type.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class GenericFieldValueFactory {

  public FieldValue<Boolean> booleanValue(Field field) {
    return new BooleanFieldValue(field);
  }

  public FieldValue<Date> dateValue(Field field) {
    return new DateFieldValue(field);
  }

  public FieldValue<DateTime> dateOnlyValue(Field field) {
    return new DateTimeFieldValue(field, Format.DATE_ONLY);
  }

  public FieldValue<DateTime> dateTimeValue(Field field) {
    return new DateTimeFieldValue(field, Format.DATE_TIME);
  }

  public FieldValue<Double> doubleValue(Field field) {
    return new DoubleFieldValue(field);
  }

  public <E extends Enum<E>> FieldValue<E> enumValue(Field field, Class<E> enumClass) {
    return new EnumFieldValue<E>(field, enumClass);
  }

  public <E extends EnumData<E>> FieldValue<E> enumDataValue(Field field, Class<E> enumClass) {
    return new EnumDataFieldValue<E>(field, enumClass);
  }
  
  public FieldValue<Long> longValue(Field field) {
    return new LongFieldValue(field);
  }

  public FieldValue<Integer> intValue(Field field) {
    return new IntegerFieldValue(field);
  }

  public FieldValue<String> stringValue(Field field) {
    return new StringFieldValue(field);
  }

  public FieldValue<DateTime> timeOnlyValue(Field field) {
    return new DateTimeFieldValue(field, Format.TIME_ONLY);
  }
}
