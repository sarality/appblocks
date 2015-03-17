package com.sarality.app.common.collect;

import java.util.HashSet;
import java.util.Set;

/**
 * Utility class to create Set objects without having to specify Template arguments.
 *
 * @author abhidep@ (Abhideep Singh)
 */
public class Sets {

  /**
   * Create a HashSet with the given elements.
   *
   * @param elements Elements to include in the set.
   * @param <T> Type of elements include in the set.
   * @return HashSet with given elements.
   */
  public static <T> Set<T> of(T... elements) {
    Set<T> set = new HashSet<T>();
    for (T element : elements) {
      set.add(element);
    }
    return set;
  }


  /**
   * Create an empty HashSet.
   *
   * @param <T> Type of elements include in the set.
   * @return An empty HashSet.
   */
  public static <T> Set<T> emptySet() {
    return new HashSet<T>();
  }
}
