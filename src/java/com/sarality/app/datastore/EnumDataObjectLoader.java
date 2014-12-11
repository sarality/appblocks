package com.sarality.app.datastore;

import com.sarality.app.common.collect.Maps;
import com.sarality.app.data.EnumData;
import com.sarality.app.data.EnumDataObject;
import com.sarality.app.data.EnumDataRegistry;

import java.util.Map;

/**
 * Loads an EnumDataObject for the given EnumData along with the associated objects.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumDataObjectLoader<E extends EnumData<E>, T extends EnumDataObject<E, T>> {

  private final Class<E> enumDataClass;
  private final EnumDataRegistry registry;
  private final EnumDataObjectComposer<E, T> composer;
  private final Map<String, AssociatedDataProvider<E, ?>> dataProviderMap = Maps.empty();

  public EnumDataObjectLoader(Class<E> enumDataClass, EnumDataRegistry registry,
      EnumDataObjectComposer<E, T> composer) {
    this.enumDataClass = enumDataClass;
    this.registry = registry;
    this.composer = composer;
  }

  public EnumDataObjectLoader withDataProvider(String componentName, AssociatedDataProvider<E, ?> dataProvider) {
    dataProviderMap.put(componentName, dataProvider);
    return this;
  }

  public T queryByName(String enumName) {
    E enumData = registry.valueOf(enumDataClass, enumName);
    return composer.compose(enumData, dataProviderMap);
  }
}
