package com.sarality.app.datastore;

import java.util.LinkedHashSet;
import java.util.Set;


public enum ColumnDataType {
  INTEGER(null),
  TEXT(null),
  BOOLEAN(ColumnDataType.INTEGER),
  DATETIME(null, ColumnFormat.DATE_AS_INT, ColumnFormat.EPOCH),
  ENUM(ColumnDataType.TEXT, ColumnFormat.ENUM_AS_INT, ColumnFormat.ENUM_AS_TXT);

  private Set<ColumnFormat> supportedFormatSet = new LinkedHashSet<ColumnFormat>();
  private ColumnDataType underlyingDataType;

  private ColumnDataType(ColumnDataType underlyingDataType, ColumnFormat... supportedFormats) {
    if (underlyingDataType == null) {
      this.underlyingDataType = this;
    } else {
      this.underlyingDataType = underlyingDataType;
    }
    for (ColumnFormat format : supportedFormats) {
      supportedFormatSet.add(format);
    }
  }

  public boolean isSupportedFormat(ColumnFormat format) {
    return supportedFormatSet.contains(format);
  }

  public ColumnDataType getUnderlyingDataType(ColumnFormat format) {
    if (format == null) {
      return underlyingDataType;
    }
    return format.getUnderlyingDataType();
  }
}
