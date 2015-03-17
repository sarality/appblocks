package com.sarality.app.data.field;

import com.sarality.app.data.field.Field.DataType;

/**
 * Base implementation of FieldValue that stores a value for the given Field.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of value stored in the FieldValue.
 */
public abstract class BaseFieldValue<T> implements FieldValue<T> {

  private T value = null;
  private boolean hasValue = false;
  private final Field field;
  private final DataType dataType;
  private final Class<T> valueClass;

  /**
   * Constructor.
   * 
   * @param field Field that this is a value.
   * @param fieldType DataType for the Field
   */
  protected BaseFieldValue(Field field, DataType dataType, Class<T> valueClass) {
    this.field = field;
    this.dataType = dataType;
    this.valueClass = valueClass;
    assertValidDataType(field, dataType);
  }

  protected void assertValidDataType(Field field, DataType dataType) {
    if (field.getDataType() != dataType) {
      throw new IllegalArgumentException("Field " + field.getName() + " has invalid data type " + field.getDataType() 
          + ". The data type should have been " + dataType);
    }
  }

  @Override
  public Field getField() {
    return field;
  }

  @Override
  public DataType getDataType() {
    return dataType;
  }

  @Override
  public boolean hasValue() {
    return hasValue;
  }

  @Override
  public T getValue() {
    if (!hasValue) {
      return null;
    }
    return value;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
    this.hasValue = true;
  }

  @Override
  public Class<T> getValueClass() {
    return valueClass;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(field.getName()).append(" : ").append(getStringValue());
    return builder.toString();
  }
}
