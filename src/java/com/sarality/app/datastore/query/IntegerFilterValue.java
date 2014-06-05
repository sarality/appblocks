package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;

/**
 * Converts an Integer value into a String, based on the Column Spec so that it can be used
 * in the query to a data source.
 * 
 * @author abhideep (Abhideep Singh)
 */
class IntegerFilterValue extends FilterValue<Integer> {

  /**
   * Package protected Constructor.
   * <p>
   * Called only by {@code FilterValue}.
   * 
   * @param value Integer value associated with the Filter.
   */
  IntegerFilterValue(Integer value) {
    super(value);
  }

  @Override
  public String getStringValue(Column column) {
    ColumnDataType dataType = column.getSpec().getDataType();
    if (dataType == ColumnDataType.INTEGER) {
      return Long.toString(getValue());
    } else {
      throw new IllegalArgumentException("Cannot set filter value of type Long on column with data type "
          + dataType);
    }
  }
}
