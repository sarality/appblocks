package com.sarality.app.datastore.query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;

/**
 * Converts a Date value into a String, based on the Column Spec so that it can be used in the query to a data source.
 * 
 * @author abhideep (Abhideep Singh)
 */
class DateFilterValue extends FilterValue<Date> {
  private final SimpleDateFormat defaultFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
  private final SimpleDateFormat dateAsIntegerFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);

  DateFilterValue(Date value) {
    super(value);
  }

  @Override
  String getStringValue(Column column) {
    ColumnDataType dataType = column.getSpec().getDataType();
    if (dataType != ColumnDataType.DATETIME) {
      throw new IllegalArgumentException("Cannot set filter value of type Date on column with data type " + dataType);
    }

    ColumnFormat format = column.getSpec().getFormat();
    if (format == null) {
      return defaultFormatter.format(getValue());
    } else if (format == ColumnFormat.EPOCH) {
      Long.valueOf(getValue().getTime());
    } else if (format == ColumnFormat.DATE_AS_INT) {
      return dateAsIntegerFormatter.format(getValue());
    }
    return defaultFormatter.format(getValue());
  }

  @Override
  int compareTo(Date value) {
    return getValue().compareTo(value);
  }
}
