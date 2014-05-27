package com.sarality.app.data;

/**
 * Interface for all Fields in a Data Object.
 * <p>
 * Fields are generally implemented as an enum which must implement this interface.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface Field {

  /**
   * @return String name for the field
   */
  public String getName();
}
