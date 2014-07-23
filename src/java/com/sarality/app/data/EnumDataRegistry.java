package com.sarality.app.data;

import java.util.HashMap;
import java.util.Map;

import com.sarality.app.common.collect.Maps;

/**
 * A Global Registry for all EnumDatas in the system.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class EnumDataRegistry {

  private static final EnumDataRegistry GLOBAL_INSTANCE = new EnumDataRegistry();
  
  // Global static map that stores a Class wise Map of Maps. The inner map has Name and DataEnum Entries.
  private final Map<Class<? extends EnumData<?>>, Map<String, EnumData<?>>> enumDataMap = Maps.empty();

  /**
   * Register a EnumData instance.
   * <p>
   * Accessed only via the BaseEnumData class.
   * 
   * @param enumClass Class of DataEnum.
   * @param enumData The EnumData instance.
   */
  <T extends EnumData<T>> void register(Class<T> enumClass, T enumData) {
    if (!enumDataMap.containsKey(enumClass)) {
      enumDataMap.put(enumClass, new HashMap<String, EnumData<?>>());
    }
    enumDataMap.get(enumClass).put(enumData.getEnumName(), enumData);
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
      return value;
    }
    return null;
  }
  
  public static final EnumDataRegistry getGlobalInstance() {
    return GLOBAL_INSTANCE;
  }
}
