package com.sarality.app.datastore;

/**
 * Interface for classes that provide associated data for a given data object or enum.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface AssociatedDataProvider<T, A> {

  public A provide(T data);
}
