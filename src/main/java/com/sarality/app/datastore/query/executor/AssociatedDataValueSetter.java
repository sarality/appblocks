package com.sarality.app.datastore.query.executor;

import com.sarality.app.data.DataObject;

import java.util.List;

/**
 * Interface for all classes that set associated data on a composite object.
 *
 * @param <T> Composite Data Object
 * @param <B> Builder for the Composite Data Object
 * @param <I> Value to lookup Associated Data
 * @param <A> Associated data object to be set
 */
public interface AssociatedDataValueSetter<T, B extends DataObject.Builder<T>, I, A> {

  /**
   * Indexes the Associated Data Objects so that they can be set on the individual data object builder by doing a
   * simple lookup on the lookup value.
   *
   * @param associatedValueList List of Associated Values that were loaded.
   */
  void indexAssociatedData(List<A> associatedValueList);

  /**
   * Given the composite Data Object provides the value that is used to lookup the AssociatedData.
   *
   * @param value Composite Data Object
   * @return The value to lookup composite data
   */
  public I getLookupValue(T value);

  /**
   * Sets the associated Data given the CompositeData object and lookup value for the Associated Data.
   *
   * @param lookupValue The value used to lookup the associated data.
   * @param builder Builder for Composite Data Object.
   */
  public void setAssociatedData(I lookupValue, B builder);

}
