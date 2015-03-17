package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;

/**
 * Converts a double value into a String, based on the Column Spec so that it can be used in the query to a data source.
 * 
 * @author abhideep (Abhideep Singh)
 */
class DoubleFilterValue extends FilterValue<Double> {

  DoubleFilterValue(Double value) {
    super(value);
  }

  @Override
  String getStringValue(Column column) {
    ColumnDataType dataType = column.getSpec().getDataType();
    if (dataType == ColumnDataType.DOUBLE) {
      return Double.toString(getValue());
    } else {
      throw new IllegalArgumentException("Cannot set filter value of type Double on column with data type " + dataType);
    }
  }

  @Override
  public int compareTo(Double value) {
    return Double.compare(getValue(), value.doubleValue());
  }
}
