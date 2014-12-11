package com.sarality.app.datastore;

import com.sarality.app.data.EnumData;
import com.sarality.app.data.EnumDataObject;

/**
 * Interface for all classes that compose an EnumDataObject.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface EnumDataObjectComposer<E extends EnumData<E>, T extends EnumDataObject<E, T>> {

  /**
   * Compose an EnumDataObject given an EumData.
   *
   * @param enumData EnumData for which the EnumDataObject is being loaded.
   * @param loader Loader which is calling this Composer to compose the EnumDataObject.
   *
   * @return EnumDataObject that was composed.
   */
  public T compose(E enumData, EnumDataObjectLoader<E, T> loader);
}
