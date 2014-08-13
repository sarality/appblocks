package com.sarality.app.data.field;

import java.util.List;
import java.util.Map;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.common.collect.Maps;
import com.sarality.app.data.DataObject.Builder;

public abstract class FieldContainer<T> {

  private final Map<Field, FieldValue<?>> fieldValueMap = Maps.empty();
  private final List<Field> fieldList = Lists.of();

  /**
   * @param values FieldValues to be registered for the Container.
   */
  protected void registerFieldValues(FieldValue<?>... values) {
    for (FieldValue<?> value : values) {
      fieldList.add(value.getField());
      fieldValueMap.put(value.getField(), value);
    }
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
   * Set the value of the field to be the given data object.
   *
   * @param field Field whose value needs to be set.
   * @param value The data for the field value.
   */
  public void setFieldValue(Field field, FieldValue<?> fieldValue) {
    if (field.getDataType() != fieldValue.getDataType()) {
      throw new IllegalArgumentException("Field " + field.getName() + " has data type " + field.getDataType() 
          + ". Cannot set the value to be a FieldValue with data type " + fieldValue.getDataType());
    }
    FieldValue<?> value = getFieldValue(field);
    value.castFrom(fieldValue.getValue());
  }

  /**
   * Populate the FieldValues for the given data object.
   * 
   * @param data Data Object to populate the FieldValues for.
   */
  public abstract void populateFieldValues(T data);

  /**
   * Create a Builder for the Data Object based on the data in the FieldValues.
   * 
   * @return
   */
  public abstract Builder<T> createBuilder();
}
