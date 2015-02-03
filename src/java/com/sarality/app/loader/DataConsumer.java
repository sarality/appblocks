package com.sarality.app.loader;

/**
 * Interface for all classes that consume the data that has been loaded by a DataLoader.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface DataConsumer<T> {

  /**
   * Inform the consumer of the data that was loaded.
   *
   * @param data Data loaded by the loader.
   */
  public void consume(T data);

}
