package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;

/**
 * Converts an Enum value into a String, based on the Column Spec so that it can be used in the query to a data source.
 * 
 * @author abhideep (Abhideep Singh)
 */
class EnumFilterValue extends FilterValue<Enum<?>> {

  EnumFilterValue(Enum<?> value) {
    super(value);
  }

  @Override
  String getStringValue(Column column) {
    ColumnDataType dataType = column.getSpec().getDataType();
    if (dataType == ColumnDataType.ENUM || dataType == ColumnDataType.TEXT) {
      return getValue().name();
    } else {
      throw new IllegalArgumentException("Cannot set filter value of type Enum on column with data type " + dataType);
    }
  }

  @Override
  public int compareTo(Enum<?> value) {
    return getValue().name().compareTo(value.name());
  }
}
