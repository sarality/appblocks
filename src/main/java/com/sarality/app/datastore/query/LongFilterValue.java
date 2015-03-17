package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;

/**
 * Converts a Long value into a String, based on the Column Spec so that it can be used in the query to a data source.
 * 
 * @author abhideep (Abhideep Singh)
 */
class LongFilterValue extends FilterValue<Long> {

  /**
   * Package protected Constructor.
   * <p>
   * Called only by {@code FilterValue}.
   * 
   * @param value Long value associated with the Filter.
   */
  LongFilterValue(Long value) {
    super(value);
  }

  @Override
  public String getStringValue(Column column) {
    Long value = getValue();
    ColumnDataType dataType = column.getSpec().getDataType();
    if (dataType == ColumnDataType.INTEGER) {
      return Long.toString(value);
    } else {
      throw new IllegalArgumentException("Cannot set filter value of type Long on column with data type " + dataType);
    }
  }

  @Override
  public int compareTo(Long value) {
    return getValue().compareTo(value);
  }
}
