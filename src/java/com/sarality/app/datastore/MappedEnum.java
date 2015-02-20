package com.sarality.app.datastore;

/**
 * Interface that must be implemented by an Enum so that it can be mapped from 
 * and to a value in a data store.
 * <p>
 * The interface is used to store data efficiently but convert it
 * to a human readable enum when read from a data store.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <V> DataType for the value
 */
public interface MappedEnum<V> {

  /**
   * @return The value that the Enum Maps to.
   */
  public V getMappedValue();

  /**
   * @return The String value that the Enum Maps to.
   */
  public String getMappedValueString();

}
