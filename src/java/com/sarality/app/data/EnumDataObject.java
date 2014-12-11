package com.sarality.app.data;

/**
 * Interface for Data Object associated with an EnumData.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface EnumDataObject<E extends EnumData<E>, T> extends DataObject<T> {

  /**
   * @return EnumData for which this is a DataObject.
   */
  public EnumData<E> getEnumData();
}
