package com.sarality.app.loader;

import com.sarality.app.data.DataObject;

/**
 * Interface for all classes that set the value for a field on a given data.
 *
 * @param <T> Type of data to set the field value on.
 * @param <V> Type of value to be set for the field.
 * @author abhideep@ (Abhideep Singh)
 */
public interface FieldValueSetter<T, V> {

  /**
   * Sets the given value for the field on the Builder.
   *
   * @param data Data to set the field value on.
   * @param value Field value to be set.
   */
  public void setValue(T data, V value);
}
