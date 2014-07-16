package com.sarality.app.data.field;

import hirondelle.date4j.DateTime;

import java.util.Date;
import java.util.Map;

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
  protected static final FieldValueFactory<Boolean> BOOLEAN_FIELD_VALUE_FACTORY = new BooleanFieldValue.Factory();
  protected static final FieldValueFactory<Date> DATE_FIELD_VALUE_FACTORY = new DateFieldValue.Factory();
  protected static final FieldValueFactory<DateTime> DATE_ONLY_FIELD_VALUE_FACTORY = 
      new DateTimeFieldValue.DateOnlyFactory();
  protected static final FieldValueFactory<DateTime> TIME_ONLY_FIELD_VALUE_FACTORY = 
      new DateTimeFieldValue.TimeOnlyFactory();
  protected static final FieldValueFactory<Double> DOUBLE_FIELD_VALUE_FACTORY = new DoubleFieldValue.Factory();
  protected static final FieldValueFactory<Long> LONG_FIELD_VALUE_FACTORY = new LongFieldValue.Factory();
  protected static final FieldValueFactory<String> STRING_FIELD_VALUE_FACTORY = new StringFieldValue.Factory();

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
   * Extract the value stored in the field.
   * 
   * @param field Field to extract value from.
   * @param fieldClass The class of the data to be retreived from the field.
   * @return value stored for the field, null if the Field does not exist or has no value.
   */
  protected <V> V getValue(Field field, Class<V> fieldClass) {
    if (hasValue(field)) {
      FieldValue<?> fieldValue = getFieldValue(field);
      assertValidDataType(field, fieldValue, fieldClass);
      @SuppressWarnings("unchecked")
      FieldValue<V> value = (FieldValue<V>) fieldValue;
      return value.getValue();
    }
    return null;    
  }

  /**
   * Set the value of the field to be the given data object.
   * 
   * @param field Field whose value needs to be set.
   * @param value The data for the field value.
   * @param factory Factory to create an instance of FieldValue that stores field, value association.
   */
  protected <V> void setFieldValue(Field field, V value, FieldValueFactory<V> factory) {
    FieldValue<V> fieldValue = factory.createFieldValue(field);
    fieldValue.setValue(value);
    fieldValueMap.put(field, fieldValue);
  }

  /**
   * Validates that the FieldValue stored for the given field does in fact have a data of the given Class.
   * <p>
   * Validation is persformed before we extract data from the FieldValue.
   * 
   * @param field Field that is being validated.
   * @param fieldValue The value for the given field.
   * @param valueClass The class of data that this field must have.
   */
  protected void assertValidDataType(Field field, FieldValue<?> fieldValue, Class<?> valueClass) {
    if (fieldValue.getValueClass() != valueClass) {
      throw new IllegalArgumentException("Field " + field.getName() + " has the Data Type " + field.getDataType()
          + " Attempting to set of get " + valueClass + " failed since it only supports getting or setting "
          + fieldValue.getClass() + "values");
    }
  }
}
