package com.sarality.app.data;

/**
 * Interface for all classes that take an input and generate a corresponding output by transforming the data or
 * creating a corresponding Data Object value.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface DataTransformer<I, T> {

  /**
   * Generate a data object for the given input value.
   *
   * @param input Input data for which data needs to generated.
   * @return Corresponding generated or transformed Data object.
   */
  public T transform(I input);
}
