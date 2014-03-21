package com.sarality.app.db;

import java.util.HashSet;
import java.util.Set;

public class ColumnData {
  public static final int DEFAULT_SIZE = -1;
  
  private final boolean isRequired;
  private final DatabaseColumn.DataType dataType;
  private final boolean hasFormat;
  private final DatabaseColumn.DataTypeFormat format;
  private final boolean hasSize;
  private final int size;
  private final Set<DatabaseColumn.Property> propertySet = new HashSet<DatabaseColumn.Property>();

  private ColumnData(DatabaseColumn.DataType dataType, boolean isRequired, DatabaseColumn.DataTypeFormat format, int size,
      Set<DatabaseColumn.Property> propertySet) {
    this.isRequired = isRequired;
    this.dataType = dataType;
    this.format = format;
    this.hasFormat = (format != null);
    this.size = size;
    this.hasSize = (size > DEFAULT_SIZE);
    this.propertySet.addAll(propertySet);
  }

  public ColumnData(DatabaseColumn.DataType dataType, boolean isRequired, DatabaseColumn.Property... properties) {
    this(dataType, isRequired, null, DEFAULT_SIZE, convertToSet(properties));
  }

  public ColumnData(DatabaseColumn.DataType dataType, DatabaseColumn.DataTypeFormat format, boolean isRequired,
      DatabaseColumn.Property... properties) {
    this(dataType, isRequired, format, DEFAULT_SIZE, convertToSet(properties));
  }

  public ColumnData(DatabaseColumn.DataType dataType, int size, boolean isRequired,
      DatabaseColumn.Property... properties) {
    this(dataType, isRequired, null, size, convertToSet(properties));
  }
  
  public ColumnData(DatabaseColumn.DataType dataType, DatabaseColumn.DataTypeFormat format, int size, boolean isRequired,
      DatabaseColumn.Property... properties) {
    this(dataType, isRequired, format, size, convertToSet(properties));
  }

  public final boolean isRequired() {
    return isRequired;
  }
  
  public final DatabaseColumn.DataType getDataType() {
    return dataType;
  }
  
  public final boolean hasFormat() {
    return hasFormat;
  }

  public final DatabaseColumn.DataTypeFormat getFormat() {
    return format;
  }

  public final boolean hasSize() {
    return hasSize;
  }

  public final int getSize() {
    return size;
  }

  public final Set<DatabaseColumn.Property> getProperties() {
    return propertySet;
  }    
  
  private static Set<DatabaseColumn.Property> convertToSet(DatabaseColumn.Property[] properties) {
    Set<DatabaseColumn.Property> propertySet = new HashSet<DatabaseColumn.Property>();
    if (properties != null) {
      for (DatabaseColumn.Property property : properties) {
        propertySet.add(property);
      }
    }
    return propertySet;
  }
}
