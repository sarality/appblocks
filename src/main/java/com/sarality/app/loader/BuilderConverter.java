package com.sarality.app.loader;

/**
 * Interface for classes that create a builder for the given data and vice-versa.
 *
 * @param <T> Type of data.
 * @param <B> Type of builder.
 * @author abhideep@ (Abhideep Singh)
 */
public interface BuilderConverter<T, B> {

  /**
   * Get the builder for the given data.
   *
   * @param data The source data.
   * @return Builder for the Data Object.
   */
  public B getBuilder(T data);

  /**
   * Generate the data for the given builder.
   *
   * @param builder The source builder.
   * @return The generated data.
   */
  public T build(B builder);
}
