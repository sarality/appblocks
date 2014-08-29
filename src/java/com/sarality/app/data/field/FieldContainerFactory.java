package com.sarality.app.data.field;

/**
 * Factory class to create a FieldContainer for a data object.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of object that this is a FieldContainer for.
 */
public interface FieldContainerFactory<T> {

  /**
   * @return New empty instance of the FieldContainer
   */
  FieldContainer<T> newContainer();
}
