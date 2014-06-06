package com.sarality.app.datastore.query;

import java.util.Date;

import com.sarality.app.datastore.Column;

/**
 * Abstract class to converts a value into a String, based on the Column Spec 
 * so that it can be used in the query to a data source.
 * 
 * @author abhideep (Abhideep Singh)
 */
public abstract class FilterValue<T> {

  // Value associated with the Filter
  private T value;
  
  /**
   * Constructor.
   * 
   * @param value Value associated with the filter.
   */
  protected FilterValue(T value) {
    this.value = value;
  }
  
  /**
   * @return Value associated with the Filter
   */
  protected T getValue() {
    return value;
  }

  /**
   * Returns String form of the value that can be used in the 
   * where clause of the filter.
   * 
   * @param column Column for which this filter value is being set.
   * @return String form of the value.
   */
  abstract String getStringValue(Column column);

  /**
   * Create a new FilterValue with the given Date.
   * 
   * @param value Value for the filter
   * @return FilterValue with the given Date.
   */
  public static FilterValue<Date> of(Date value) {
    return new DateFilterValue(value);
  }

  /**
   * Create a new FilterValue with the given Double value.
   * 
   * @param value Value for the filter
   * @return FilterValue with the given Double.
   */
  public static FilterValue<Double> of(Double value) {
    return new DoubleFilterValue(value);
  }

  /**
   * Create a new FilterValue with the given Enum.
   * 
   * @param value Value for the filter
   * @return FilterValue with the given Enum.
   */  
  public static FilterValue<Enum<?>> of(Enum<?> value) {
    return new EnumFilterValue(value);
  }  
  
  /**
   * Create a new FilterValue with the given Integer.
   * 
   * @param value Value for the filter
   * @return FilterValue with the given Integer.
   */  
  public static FilterValue<Integer> of(Integer value) {
    return new IntegerFilterValue(value);
  }
  
  /**
   * Create a new FilterValue with the given Long.
   * 
   * @param value Value for the filter
   * @return FilterValue with the given Long.
   */  
  public static FilterValue<Long> of(Long value) {
    return new LongFilterValue(value);
  }

  /**
   * Create a new FilterValue with the given String.
   * 
   * @param value Value for the filter
   * @return FilterValue with the given String.
   */
  public static FilterValue<String> of(String value) {
    return new StringFilterValue(value);
  }
}
