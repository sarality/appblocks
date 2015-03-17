package com.sarality.app.datastore.query;

import java.util.Date;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.MappedEnum;

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
    if (value == null) {
      throw new IllegalArgumentException("Cannot filter on a null value. Use the IS_NULL "
          + "or IS_NOT_NULL operator instead");
    }
    this.value = value;
  }
  
  /**
   * @return Value associated with the Filter
   */
  public T getValue() {
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
  
  protected T castTo(Object object) {
    try {
      @SuppressWarnings("unchecked")
      T value = (T) object;
      return value;
    } catch (ClassCastException cce) {
      throw new IllegalArgumentException("Filter and Value mismatch");
    }
  }

  /**
   * Compare Filter value against a given value.
   * 
   * @param value Value to compare against
   * @return 0 if values are equal, +1 is the given value is greater
   */
  abstract int compareTo(T value);

  /**
   * Indicates whether the FilterValue matches(passes) the condition specified by the operator and value
   * <P>
   * Checks if (DataValue Operator FilterValue) is true.
   * 
   * @param operator Operator for the filter.
   * @param value Value of the data object that we need to compare against
   * @return true if the condition is met, false otherwise.
   */
  public boolean matches(Operator operator, Object value) {
    if (Operator.IS_NULL.equals(operator)) {
      return value == null;
    } else if (Operator.IS_NOT_NULL.equals(operator)) {
      return value != null;
    }

    if (value == null) {
      // A null object cannot match anything;
      return false;
    }

    if (Operator.EQUALS.equals(operator)) {
      return compareTo(castTo(value)) == 0;
    } else if (Operator.NOT_EQUALS.equals(operator)) {
      return compareTo(castTo(value)) != 0;
    } else if (Operator.GREATER_THAN.equals(operator)) {
      // It is reverse of operator since we compare value of filter against given data
      return compareTo(castTo(value)) < 0;
    } else if (Operator.LESS_THAN.equals(operator)) {
      // It is reverse of operator since we compare value of filter against given data
      return compareTo(castTo(value)) > 0;
    }
    throw new IllegalStateException("Operator " + operator.name() + " not supported for filters");
  }

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
   * Create a new FilterValue with the given Mapped Enum.
   *
   * @param value Value for the filter
   * @return FilterValue with the given Mapped Enum.
   */
  public static <V> FilterValue<MappedEnum<V>> ofMappedEnum(MappedEnum<V> value) {
    return new MappedEnumFilterValue<V>(value);
  }

  /**
   * Create a new FilterValue with the given Enum.
   * 
   * @param value Value for the filter
   * @return FilterValue with the given Enum.
   */  
  public static <V extends Enum<V>> FilterValue<Enum<V>> of(Enum<V> value) {
    return new EnumFilterValue<V>(value);
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
