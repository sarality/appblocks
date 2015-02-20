package com.sarality.app.datastore.query;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.MappedEnum;

/**
 * Converts an Enum value into a String, based on the Column Spec so that it can be used in the query to a data source.
 * 
 * @author abhideep (Abhideep Singh)
 */
class MappedEnumFilterValue<V> extends FilterValue<MappedEnum<V>> {

  MappedEnumFilterValue(MappedEnum<V> value) {
    super(value);
  }

  @Override
  String getStringValue(Column column) {
    ColumnDataType dataType = column.getSpec().getDataType();
    if (dataType == ColumnDataType.ENUM || dataType == ColumnDataType.TEXT || dataType == ColumnDataType.INTEGER) {
      return getValue().getMappedValueString();
    } else {
      throw new IllegalArgumentException("Cannot set filter value of type Enum on column with data type " + dataType);
    }
  }

  @Override
  public int compareTo(MappedEnum<V> value) {
    return getValue().getMappedValueString().compareTo(value.getMappedValueString());
  }
}
