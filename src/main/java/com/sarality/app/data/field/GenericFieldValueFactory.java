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

  public FieldValue<Boolean> booleanValue(Field field, Boolean value) {
    FieldValue<Boolean> fieldValue = new BooleanFieldValue(field);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public FieldValue<Date> dateValue(Field field) {
    return new DateFieldValue(field);
  }

  public FieldValue<Date> dateValue(Field field, Date value) {
    FieldValue<Date> fieldValue = new DateFieldValue(field);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public FieldValue<DateTime> dateOnlyValue(Field field) {
    return new DateTimeFieldValue(field, Format.DATE_ONLY);
  }

  public FieldValue<DateTime> dateOnlyValue(Field field, DateTime value) {
    FieldValue<DateTime> fieldValue = new DateTimeFieldValue(field, Format.DATE_ONLY);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public FieldValue<DateTime> dateTimeValue(Field field) {
    return new DateTimeFieldValue(field, Format.DATE_TIME);
  }

  public FieldValue<DateTime> dateTimeValue(Field field, DateTime value) {
    FieldValue<DateTime> fieldValue = new DateTimeFieldValue(field, Format.DATE_TIME);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public FieldValue<Double> doubleValue(Field field) {
    return new DoubleFieldValue(field);
  }

  public FieldValue<Double> doubleValue(Field field, Double value) {
    FieldValue<Double> fieldValue = new DoubleFieldValue(field);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public <E extends Enum<E>> FieldValue<E> enumValue(Field field, Class<E> enumClass) {
    return new EnumFieldValue<E>(field, enumClass);
  }

  public <E extends Enum<E>> FieldValue<E> enumValue(Field field, Class<E> enumClass, E value) {
    FieldValue<E> fieldValue = new EnumFieldValue<E>(field, enumClass);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public <E extends EnumData<E>> FieldValue<E> enumDataValue(Field field, Class<E> enumClass) {
    return new EnumDataFieldValue<E>(field, enumClass);
  }

  public <E extends EnumData<E>> FieldValue<E> enumDataValue(Field field, Class<E> enumClass, E value) {
    FieldValue<E> fieldValue = new EnumDataFieldValue<E>(field, enumClass);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public FieldValue<Integer> intValue(Field field) {
    return new IntegerFieldValue(field);
  }

  public FieldValue<Integer> intValue(Field field, Integer value) {
    FieldValue<Integer> fieldValue = new IntegerFieldValue(field);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public FieldValue<Long> longValue(Field field) {
    return new LongFieldValue(field);
  }

  public FieldValue<Long> longValue(Field field, Long value) {
    FieldValue<Long> fieldValue = new LongFieldValue(field);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public FieldValue<String> stringValue(Field field) {
    return new StringFieldValue(field);
  }

  public FieldValue<String> stringValue(Field field, String value) {
    FieldValue<String> fieldValue = new StringFieldValue(field);
    fieldValue.setValue(value);
    return fieldValue;
  }

  public FieldValue<DateTime> timeOnlyValue(Field field) {
    return new DateTimeFieldValue(field, Format.TIME_ONLY);
  }

  public FieldValue<DateTime> timeOnlyValue(Field field, DateTime value) {
    FieldValue<DateTime> fieldValue = new DateTimeFieldValue(field, Format.TIME_ONLY);
    fieldValue.setValue(value);
    return fieldValue;
  }

}
