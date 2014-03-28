package com.sarality.app.data;
/**
 * Interface for all data objects.
 * <p>
 * Data objects meant to be read only objects that only have getter methods
 * and an empty contructor. The are generated using a corresponding Builder
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 */
public interface DataObject<T> {

  /**
   * Returns a Builder with the data of data object already populated.
   * <p>
   * Since data objects are immutable, we need to create a Builder in
   * order to create a new data objects with modified data.
   * 
   * @return a Builder with the data of data object already populated.
   */
  public Builder<T> getBuilder();

  /**
   * @return An empty builder for the data object
   */
  public Builder<T> newBuilder();
  
  /**
   * Interface for all Builder classes that create a Data Object.
   * 
   * @author abhideep@ (Abhideep Singh)
   *
   * @param <T> The type of data object that the Builder builds
   */
  public interface Builder<T> {
    public T build();
  }
}
