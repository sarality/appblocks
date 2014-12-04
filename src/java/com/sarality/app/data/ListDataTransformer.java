package com.sarality.app.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms a list of data objects by delegating the transformation to another transformer and applying the
 * transformation to all elements of the list;
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListDataTransformer<I, T> implements DataTransformer<List<I>, List<T>> {

  private final DataTransformer<I, T> transformer;

  public ListDataTransformer(DataTransformer<I, T> transformer) {
    this.transformer = transformer;
  }

  @Override
  public List<T> transform(List<I> inputList) {
    if (inputList == null) {
      return null;
    }
    List<T> outputList = new ArrayList<T>();
    for (I input : inputList) {
      outputList.add(transformer.transform(input));
    }
    return outputList;
  }
}
