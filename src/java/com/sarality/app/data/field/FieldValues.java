package com.sarality.app.data.field;

import hirondelle.date4j.DateTime;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sarality.app.common.collect.Lists;
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
  protected static final FieldValueFactory<DateTime> DATE_TIME_FIELD_VALUE_FACTORY = 
      new DateTimeFieldValue.Factory();
  protected static final FieldValueFactory<DateTime> DATE_ONLY_FIELD_VALUE_FACTORY = 
      new DateTimeFieldValue.DateOnlyFactory();
  protected static final FieldValueFactory<DateTime> TIME_ONLY_FIELD_VALUE_FACTORY = 
      new DateTimeFieldValue.TimeOnlyFactory();
  protected static final FieldValueFactory<Double> DOUBLE_FIELD_VALUE_FACTORY = new DoubleFieldValue.Factory();
  protected static final FieldValueFactory<Integer> INTEGER_FIELD_VALUE_FACTORY = new IntegerFieldValue.Factory();
  protected static final FieldValueFactory<Long> LONG_FIELD_VALUE_FACTORY = new LongFieldValue.Factory();
  protected static final FieldValueFactory<String> STRING_FIELD_VALUE_FACTORY = new StringFieldValue.Factory();

  private static final Map<Field.DataType, FieldValueFactory<?>> DATA_TYPE_FIELD_VALUE_FACTORY_MAP = 
      Maps.<Field.DataType, FieldValueFactory<?>>builder()
      .with(Field.DataType.BOOLEAN, BOOLEAN_FIELD_VALUE_FACTORY)
      .with(Field.DataType.DATE, DATE_FIELD_VALUE_FACTORY)
      .with(Field.DataType.DATETIME, DATE_TIME_FIELD_VALUE_FACTORY)
      .with(Field.DataType.DATE_ONLY, DATE_ONLY_FIELD_VALUE_FACTORY)
      .with(Field.DataType.DOUBLE, DOUBLE_FIELD_VALUE_FACTORY)
      .with(Field.DataType.INTEGER, INTEGER_FIELD_VALUE_FACTORY)
      .with(Field.DataType.LONG, LONG_FIELD_VALUE_FACTORY)
      .with(Field.DataType.STRING, STRING_FIELD_VALUE_FACTORY)
      .with(Field.DataType.TIME_ONLY, TIME_ONLY_FIELD_VALUE_FACTORY)
      .build();

  // Map between Field and FieldValue. A Map makes for an easy O(1) lookup.
  private final Map<Field, FieldValue<?>> fieldValueMap = Maps.empty();
  private final List<Field> fieldList = Lists.of();
  private final Map<Field.DataType, FieldValueFactory<?>> dataTypeFieldValueFactoryMap = Maps.empty();
  private final Map<Field, FieldValueFactory<?>> fieldValueFactoryMap = Maps.empty();

  protected FieldValues(Field[] fields) {
    this(fields, null, null, null);
  }

  protected FieldValues(Field[] fields, T data) {
    this(fields, data, null, null);
  }
  
  protected FieldValues(Field[] fields, T data, Map<Field.DataType, FieldValueFactory<?>> dataTypeFactoryMap, 
      Map<Field, FieldValueFactory<?>> fieldValueFactoryMap) {
    if (fields != null) {
      for (Field field : fields) {
        fieldValueMap.put(field, null);
        fieldList.add(field);
      }
    }

    // You can either pass the Map in or call the register method in the constructor
    if (dataTypeFactoryMap != null) {
      for (Map.Entry<Field.DataType, FieldValueFactory<?>> entry : dataTypeFactoryMap.entrySet()) {
        registerFieldValueFactory(entry.getKey(), entry.getValue());
      }
    }

    // You can either pass the Map in or call the register method in the constructor
    if (fieldValueFactoryMap != null) {
      for (Map.Entry<Field, FieldValueFactory<?>> entry : fieldValueFactoryMap.entrySet()) {
        registerFieldValueFactory(entry.getKey(), entry.getValue());
      }
    }

    if (data != null) {
      populateFieldValues(data);
    }
  }

  protected void registerFieldValueFactory(Field.DataType dataType, FieldValueFactory<?> factory) {
    this.dataTypeFieldValueFactoryMap.put(dataType, factory);
  }
  
  protected void registerFieldValueFactory(Field field, FieldValueFactory<?> factory) {
    this.fieldValueFactoryMap.put(field, factory);
  }

  protected FieldValueFactory<?> getFieldValueFactory(Field field) {
    if (fieldValueFactoryMap != null && fieldValueFactoryMap.containsKey(field)) {
      return fieldValueFactoryMap.get(field);
    }
    Field.DataType dataType = field.getDataType();
    if (dataTypeFieldValueFactoryMap != null && dataTypeFieldValueFactoryMap.containsKey(dataType)) {
      return dataTypeFieldValueFactoryMap.get(dataType);
    }
    return DATA_TYPE_FIELD_VALUE_FACTORY_MAP.get(dataType);
  }
  /**
   * @return List of Fields for the given object.
   */
  public List<Field> getFields() {
    return fieldList;
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
   * Extract the value stored in the field.
   * 
   * @param field Field to extract value from.
   * @param fieldClass The class of the data to be retrieved from the field.
   * @return value stored for the field, null if the Field does not exist or has no value.
   */
  public <V> V getValue(Field field) {
    if (hasValue(field)) {
      FieldValue<?> fieldValue = getFieldValue(field);
      @SuppressWarnings("unchecked")
      V value = (V) fieldValue.getValue();
      return value;
    }
    return null;    
  }
  
  /**
   * Set the value of the field to be the given FieldValue.
   * 
   * @param fieldValue The FieldValue defining the Field and its value.
   */
  public <V> void setFieldValue(FieldValue<V> fieldValue) {
    assertValidDataType(fieldValue.getField(), fieldValue, fieldValue.getValueClass());
    fieldValueMap.put(fieldValue.getField(), fieldValue);
  }

  /**
   * Set the value of the field to be the given data object.
   * 
   * @param field Field whose value needs to be set.
   * @param value The data for the field value.
   * @param factory Factory to create an instance of FieldValue that stores field, value association.
   */
  protected <V> void setFieldValue(Field field, V value) {
    FieldValue<V> fieldValue = createFieldValue(field, value);
    fieldValueMap.put(field, fieldValue);
  }

  public <V> FieldValue<V> createFieldValue(Field field, V value) {
    FieldValueFactory<?> factory = getFieldValueFactory(field);
    @SuppressWarnings("unchecked")
    FieldValue<V> fieldValue = (FieldValue<V>) factory.createFieldValue(field);
    fieldValue.setValue(value);
    return fieldValue;
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
