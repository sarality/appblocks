package com.sarality.app.data;

/**
 * Interface to load an EnumData from a Persistent store.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface EnumDataLoader<E extends EnumData<E>> {

  /**
   * Load the EnumData from the persistent store.
   *
   * @param name Unique name of the Enum.
   * @return The EnumData with the given name if one is found, {@code null} otherwise.
   */
  public E load(String name);
}
