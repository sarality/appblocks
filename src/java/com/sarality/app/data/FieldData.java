package com.sarality.app.data;

/**
 * Interface for all classes that represent a Field and its value.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of data stored in the FieldData.
 */
public interface FieldData<T> {

  /**
   * Data Type for the Field.
   */
  public enum Type {
    BOOLEAN,
    DATE,
    DOUBLE,
    ENUM,
    INT,
    LONG,
    STRING,
    COMPLEX,  
  }

  /**
   * @return Field that this is the data for.
   */
  public Field getField();
  
  /**
   * @return The Data Type for the Field.
   */
  public Type getType();

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
}
