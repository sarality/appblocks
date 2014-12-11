package com.sarality.app.datastore;

import com.sarality.app.data.EnumData;
import com.sarality.app.data.EnumDataObject;

import java.util.Map;

/**
 * Interface for all classes that compose an EnumDataObject.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface EnumDataObjectComposer<E extends EnumData<E>, T extends EnumDataObject<E, T>> {

  /**
   * Compose an EnumDataObject given an EumData.
   *
   * @param enumData
   * @param componentProviderMap
   * @return EnumDataObject that was composed.
   */
  public T compose(E enumData, Map<String, AssociatedDataProvider<E, ?>> componentProviderMap);
}
