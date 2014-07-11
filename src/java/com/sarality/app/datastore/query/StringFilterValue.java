package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;

/**
 * Converts a String value into String, based on the Column Spec so that it can be used
 * in the query to a data source.
 * 
 * @author abhideep (Abhideep Singh)
 */
class StringFilterValue extends FilterValue<String> {

  /**
   * Package protected Constructor.
   * <p>
   * Called only by {@code FilterValue}.
   * 
   * @param value String value associated with the Filter.
   */
  StringFilterValue(String value) {
    super(value);
  }

  @Override
  public String getStringValue(Column column) {
    ColumnDataType dataType = column.getSpec().getDataType();
    if (dataType == ColumnDataType.TEXT || dataType == ColumnDataType.ENUM) {
      return getValue();
    } else {
      throw new IllegalArgumentException("Cannot set filter value of type String on column with data type "
          + dataType);
    }
  }

  @Override
  public int compareTo(String value) {
    return getValue().compareTo(value);
  }
}
