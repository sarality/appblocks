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

  /**
   * Default Constructor.
   */
  public EnumDataObjectLoader(Class<E> enumDataClass, EnumDataObjectComposer<E, T> composer) {
    this(enumDataClass, EnumDataRegistry.getGlobalInstance(), composer);
  }

  /**
   * Constructor for Testing
   */
  public EnumDataObjectLoader(Class<E> enumDataClass, EnumDataRegistry registry,
      EnumDataObjectComposer<E, T> composer) {
    this.enumDataClass = enumDataClass;
    this.registry = registry;
    this.composer = composer;
  }

  /**
   * Query for the EnumDataObject by name.
   *
   * @param enumName Name of the EnumData that we are loading.
   * @return DataObject associated with the EnumData with the given name.
   */
  public T queryByName(String enumName) {
    E enumData = registry.valueOf(enumDataClass, enumName);
    return composer.compose(enumData, this);
  }

  /**
   * Defines which parts/components of the composite Data object needed to be loaded for the EnumDataObject being
   * loaded.
   *
   * @param componentName Name of the Component being loaded.
   * @param dataProvider The data provider that provides the data associated data for that component.
   * @return A loader that will load the configured associated data.
   */
  public EnumDataObjectLoader withDataProvider(String componentName, AssociatedDataProvider<E, ?> dataProvider) {
    dataProviderMap.put(componentName, dataProvider);
    return this;
  }

  /**
   * Loads the Associated Data from the Provider for the Component with the given Name.
   *
   * @param enumData EnumData for which the AssociatedData is being loaded.
   * @param componentName Name of the Component / Associated Data
   *
   * @param <A> Type of Associated Data.
   * @return Associated Data returned by the provider.
   */
  public <A> A getAssociatedData(E enumData, String componentName) {
    if (dataProviderMap.containsKey(componentName) && dataProviderMap.get(componentName) != null) {
      return (A) dataProviderMap.get(componentName).provide(enumData);
    }
    return null;
  }
}
