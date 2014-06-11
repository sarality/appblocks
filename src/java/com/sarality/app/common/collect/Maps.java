package com.sarality.app.common.collect;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to create Map objects without having to
 * specify Template arguments.
 * <p>
 * Inspired from Google's Guava Library.
 * 
 * @author abhidep@ (Abhideep Singh)
 */
public class Maps {

  /**
   * @return An empty HashMap.
   */
  public static <K, V> Map<K, V> empty() {
    return new Builder<K, V>().build();
  }
  
  /**
   * @return A Map builder that you can add values to
   */
  public static <K, V> Builder<K, V> builder() {
    return new Builder<K, V>();
  }

  /**
   * Builder to create an instance of a Map.
   * 
   * @author abhideep@ (Abhideep Singh)
   *
   * @param <K> The type of key
   * @param <V> The type of value
   */
  public static class Builder<K, V> {
    private final Map<K, V> map = new HashMap<K, V>();
    
    /**
     * Add key, value pair to the map.
     * 
     * @param key Key for the Map entry.
     * @param value Value for the Map entry.
     * @return The modified Map builder with new entry added.
     */
    public Builder<K,V> with(K key, V value) {
      map.put(key, value);
      return this;
    }
    
    public Map<K, V> build() {
      return map;
    }
  }
}
