package com.sarality.app.common.collect;

/**
 * Interface for all classes that represent an if statement.
 * <p>
 * It uses the input object to return a true or false value, and is used in validation or filtering.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of object to run the if statement on.
 */
public interface Predicate<T> {

  /**
   * Indicates if the condition applies to the given object.
   * 
   * @param data The object to run the condition on.
   * @return {@code true} if the condition is met, {@code} false otherwise.
   */
  public boolean apply(T data);
}
