package com.sarality.app.data;

import com.sarality.app.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * A Global Registry for all EnumDatas in the system.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumDataRegistry {

  private static final EnumDataRegistry GLOBAL_INSTANCE = new EnumDataRegistry();
  
  // Global static map that stores a Class wise Map of Maps. The inner map has Name and DataEnum Entries.
  private final Map<Class<? extends EnumData<?>>, Map<String, EnumData<?>>> enumDataMap = Maps.empty();
  private final Map<Class<? extends EnumData<?>>, EnumDataLoader<?>> enumDataLoaderMap = Maps.empty();

  /**
   * Register a EnumData instance.
   * <p>
   * Accessed only via the BaseEnumData class.
   * 
   * @param enumClass Class of DataEnum.
   * @param enumData The EnumData instance.
   * @param <T> The type of EnumData
   */
  <T extends EnumData<T>> void register(Class<T> enumClass, T enumData) {
    if (!enumDataMap.containsKey(enumClass)) {
      enumDataMap.put(enumClass, new HashMap<String, EnumData<?>>());
    }
    enumDataMap.get(enumClass).put(enumData.getEnumName(), enumData);
  }

  /**
   * Register the loader to load the EnumData from a Persistent Store.
   * <p/>
   * Used when EnumData with given name is not found.
   *
   * @param enumClass Class of EnumData.
   * @param loader The loader to load the EnumData from
   * @param <T> The type of EnumData
   */
  public <T extends EnumData<T>> void registerLoader(Class<T> enumClass, EnumDataLoader<T> loader) {
    enumDataLoaderMap.put(enumClass, loader);
  }

  /**
   * Retrieve the EnumData instance for the given Class and name.
   * 
   * @param enumClass Class of EnumData to be retrieved.
   * @param name String name for the EnumData.
   * @return EnumData instance with given Class and name.
   */
  public <E extends EnumData<E>> E valueOf(Class<E> enumClass, String name) {
    Map<String, EnumData<?>> dataMap = enumDataMap.get(enumClass);
    if (dataMap != null) {
      @SuppressWarnings("unchecked")
      E value = (E) dataMap.get(name);
      if (value != null) {
        return value;
      }
    }
    return loadFromLoader(enumClass, name);
  }

  private <E extends EnumData<E>> E loadFromLoader(Class<E> enumClass, String name) {
    if (enumDataLoaderMap.containsKey(enumClass)) {
      @SuppressWarnings("unchecked")
      EnumDataLoader<E> loader = (EnumDataLoader<E>) enumDataLoaderMap.get(enumClass);
      if (loader != null) {
        return loader.load(name);
      }
    }
    return null;
  }

  public static final EnumDataRegistry getGlobalInstance() {
    return GLOBAL_INSTANCE;
  }
}
