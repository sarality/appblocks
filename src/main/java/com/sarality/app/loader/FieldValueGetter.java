package com.sarality.app.loader;

/**
 * Interface for all classes that extract the value for a field from the given data.
 *
 * @param <T> Type of data to extract value from.
 * @param <V> Type of value to be extracted.
 * @author abhideep@ (Abhideep Singh)
 */
public interface FieldValueGetter<T, V> {

  /**
   * Returns the value extracted from the data.
   *
   * @param data Data to extract value from.
   * @return Extracted value
   */
  public V getValue(T data);
}
