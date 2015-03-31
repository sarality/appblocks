package com.sarality.app.common.collect;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to create List objects without having to
 * specify Template arguments.
 * <p>
 * Inspired from Google's Guava Library.
 * 
 * @author abhidep@ (Abhideep Singh)
 */
public class Lists {

  /**
   * Create an ArrayList with the given elements.
   * 
   * @param elements Elements to include in the list.
   * @param <T> Type of elements include in the list.
   * @return ArrayList with given elements.
   */
  public static <T> List<T> of(T... elements) {
    List<T> list = new ArrayList<T>();
    for (T element : elements) {
      list.add(element);
    }
    return list;
  }

  /**
   * Create an empty ArrayList.
   *
   * @param <T> Type of elements include in the list.
   * @return An empty ArrayList.
   */
  public static <T> List<T> emptyList() {
    return new ArrayList<T>();
  }
}
