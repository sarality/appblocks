package com.sarality.app.datastore;

import com.sarality.app.data.EnumData;

/**
 * Interface for all classes that provide an EnumData.
 * <p>
 * Generally, implemented by java Enum classes.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface EnumDataProvider<E extends EnumData> {

  /**
   * @return EnumData instance for the Provider.
   */
  public E getEnumData();
}
