package com.sarality.app.data.field;

import com.sarality.app.data.field.Field.DataType;

/**
 * Interface for all classes that store data for a given field.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of value stored in the FieldValue.
 */
public interface FieldValue<T> {

  /**
   * @return The Field that this is the value of.
   */
  public Field getField();
  
  /**
   * @return The Data Type for the Field.
   */
  public DataType getDataType();

  /**
   * @return {@code true} if the field has a value, {@code false} otherwise.
   */
  public boolean hasValue();

  /**
   * @return The value associated with the Field or null if the field has no value.
   */
  public T getValue();

  /**
   * Set the value for the field to the given value.
   * 
   * @param value The value to be set for the field.
   */
  public void setValue(T value);

  /**
   * Set the value for the field by casting it from the given value.
   * 
   * @param value The value to be set for the field.
   */
  public void castFrom(Object value);

  /**
   * Set the value for the field by parsing it from the given String value.
   * 
   * @param stringValue The value to set for the field.
   */
  public void parseFrom(String stringValue);

  /**
   * @return String representation of the field data
   */
  public String getStringValue();

  /**
   * @return The Class of the Value store in the FieldValue.
   */
  public Class<?> getValueClass();
}
