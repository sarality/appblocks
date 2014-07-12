package com.sarality.app.data.field;

import java.util.Date;
import java.util.Map;

import com.dothat.app.module.flight.data.FlightDataField;
import com.sarality.app.common.collect.Maps;
import com.sarality.app.data.DataObject.Builder;

/**
 * A set of FieldValues for a given object.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of object that this is FieldValueSet for.
 */
public abstract class FieldValues<T> {

  // Statically defined global Factories that can be used to create FieldValue instance.
  private static final BooleanFieldValue.Factory BOOLEAN_FIELD_VALUE_FACTORY = new BooleanFieldValue.Factory();
  private static final DateFieldValue.Factory DATE_FIELD_VALUE_FACTORY = new DateFieldValue.Factory();
  private static final DoubleFieldValue.Factory DOUBLE_FIELD_VALUE_FACTORY = new DoubleFieldValue.Factory();
  private static final LongFieldValue.Factory LONG_FIELD_VALUE_FACTORY = new LongFieldValue.Factory();
  private static final StringFieldValue.Factory STRING_FIELD_VALUE_FACTORY = new StringFieldValue.Factory();

  // Map between Field and FieldValue. A Map makes for an easy O(1) lookup.
  private Map<Field, FieldValue<?>> fieldValueMap = Maps.empty();

  protected FieldValues(Field[] fields) {
    if (fields != null) {
      for (Field field : fields) {
        fieldValueMap.put(field, null);
      }
    }
  }

  /**
   * Returns the FieldValue for a given field.
   * 
   * @param field Field that you need the value for.
   * @return FieldValue for given field, null is no such field has been added.
   */
  public FieldValue<?> getFieldValue(Field field) {
    return fieldValueMap.get(field);
  }

  /**
   * Indicates whether the FieldSet has the given Field.
   * 
   * @param field Field to lookup in the FieldSet.
   * @return {code true} if the Field exists in the Set, {@code false} otherwise.
   */
  public boolean hasField(Field field) {
    return fieldValueMap.containsKey(field);
  }

  /**
   * Indicates whether the FieldSet has the given Field and if that Field has a Value.
   * 
   * @param field Field that we are checking the value for.
   * @return {code true} if the Field exists in the Set and has a value, {@code false} otherwise.
   */
  public boolean hasValue(Field field) {
    if (hasField(field)) {
      FieldValue<?> fieldValue = fieldValueMap.get(field);
      if (fieldValue != null) {
        return fieldValue.hasValue();
      }
    }
    return false;
  }

  /**
   * Populate the FieldValues for the given data object.
   * 
   * @param data Data Object to populate the FieldValues for.
   */
  public abstract void populateFieldValues(T data);

  /**
   * Create a Builder for the FieldValues that exist in
   * 
   * @return
   */
  public abstract Builder<T> createBuilder();

  /**
   * Sets the Field's value to bet the given Boolean object.
   * 
   * @param field Field that we are setting the value for.
   * @param value Boolean value being set for the field.
   */
  protected void setFieldValue(Field field, Boolean value) {
    FieldValue<Boolean> fieldValue = BOOLEAN_FIELD_VALUE_FACTORY.createFieldValue(field);
    fieldValue.setValue(value);
    fieldValueMap.put(field, fieldValue);
  }

  /**
   * Extract the Boolean value stored in the Field.
   * 
   * @param field Field to extract value from.
   * @return Boolean value stored for the field, null if the Field does not exist or has no value.
   * @throws IllegalArgumentException If the Field does not store a Boolean value.
   */
  protected Boolean getBooleanValue(FlightDataField field) {
    if (hasValue(field)) {
      FieldValue<?> fieldValue = getFieldValue(field);
      assertValidDataType(field, fieldValue, Boolean.class);
      @SuppressWarnings("unchecked")
      FieldValue<Boolean> value = (FieldValue<Boolean>) fieldValue;
      return value.getValue();
    }
    return null;
  }

  /**
   * Sets the Field's value to bet the given Date object.
   * 
   * @param field Field that we are setting the value for.
   * @param value Date value being set for the field.
   */
  protected void setFieldValue(Field field, Date value) {
    FieldValue<Date> fieldValue = DATE_FIELD_VALUE_FACTORY.createFieldValue(field);
    fieldValue.setValue(value);
    fieldValueMap.put(field, fieldValue);
  }

  /**
   * Extract the Date value stored in the Field.
   * 
   * @param field Field to extract value from.
   * @return Date value stored for the field, null if the Field does not exist or has no value.
   * @throws IllegalArgumentException If the Field does not store a Date value.
   */
  protected Date getDateValue(FlightDataField field) {
    if (hasValue(field)) {
      FieldValue<?> fieldValue = getFieldValue(field);
      assertValidDataType(field, fieldValue, Date.class);
      @SuppressWarnings("unchecked")
      FieldValue<Date> value = (FieldValue<Date>) fieldValue;
      return value.getValue();
    }
    return null;
  }

  /**
   * Sets the Field's value to bet the given Double object.
   * 
   * @param field Field that we are setting the value for.
   * @param value Double value being set for the field.
   */
  protected void setFieldValue(Field field, Double value) {
    FieldValue<Double> fieldValue = DOUBLE_FIELD_VALUE_FACTORY.createFieldValue(field);
    fieldValue.setValue(value);
    fieldValueMap.put(field, fieldValue);
  }

  /**
   * Extract the Double value stored in the Field.
   * 
   * @param field Field to extract value from.
   * @return Double value stored for the field, null if the Field does not exist or has no value.
   * @throws IllegalArgumentException If the Field does not store a Double value.
   */
  protected Double getDoubleValue(FlightDataField field) {
    if (hasValue(field)) {
      FieldValue<?> fieldValue = getFieldValue(field);
      assertValidDataType(field, fieldValue, Double.class);
      @SuppressWarnings("unchecked")
      FieldValue<Double> value = (FieldValue<Double>) fieldValue;
      return value.getValue();
    }
    return null;
  }

  /**
   * Sets the Field's value to bet the given Long object.
   * 
   * @param field Field that we are setting the value for.
   * @param value Long value being set for the field.
   */
  protected void setFieldValue(Field field, Long value) {
    FieldValue<Long> fieldValue = LONG_FIELD_VALUE_FACTORY.createFieldValue(field);
    fieldValue.setValue(value);
    fieldValueMap.put(field, fieldValue);
  }

  /**
   * Extract the Long value stored in the Field.
   * 
   * @param field Field to extract value from.
   * @return Long value stored for the field, null if the Field does not exist or has no value.
   * @throws IllegalArgumentException If the Field does not store a Long value.
   */
  protected Long getLongValue(FlightDataField field) {
    if (hasValue(field)) {
      FieldValue<?> fieldValue = getFieldValue(field);
      assertValidDataType(field, fieldValue, Long.class);
      @SuppressWarnings("unchecked")
      FieldValue<Long> value = (FieldValue<Long>) fieldValue;
      return value.getValue();
    }
    return null;
  }

  /**
   * Sets the Field's value to bet the given String object.
   * 
   * @param field Field that we are setting the value for.
   * @param value String value being set for the field.
   */
  protected void setFieldValue(Field field, String value) {
    FieldValue<String> fieldValue = STRING_FIELD_VALUE_FACTORY.createFieldValue(field);
    fieldValue.setValue(value);
    fieldValueMap.put(field, fieldValue);
  }

  /**
   * Extract the String value stored in the Field.
   * 
   * @param field Field to extract value from.
   * @return String value stored for the field, null if the Field does not exist or has no value.
   * @throws IllegalArgumentException If the Field does not store a Long value.
   */
  protected String getStringValue(FlightDataField field) {
    if (hasValue(field)) {
      FieldValue<?> fieldValue = getFieldValue(field);
      assertValidDataType(field, fieldValue, String.class);
      @SuppressWarnings("unchecked")
      FieldValue<String> value = (FieldValue<String>) fieldValue;
      return value.getValue();
    }
    return null;
  }

  protected void assertValidDataType(Field field, FieldValue<?> fieldValue, Class<?> valueClass) {
    if (fieldValue.getValueClass() != valueClass) {
      throw new IllegalArgumentException("Field " + field.getName() + " has the Data Type " + field.getDataType()
          + " Attempting to set of get " + valueClass + " failed since it only supports getting or setting "
          + fieldValue.getClass() + "values");
    }
  }

}
