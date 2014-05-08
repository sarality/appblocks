package com.sarality.app.datastore;

import java.util.HashSet;
import java.util.Set;

public class ColumnSpec {
  
  // The data type for the column
  private final ColumnDataType dataType;
  // Data types like date can be saved in multiple formats like EPoch or as integers.
  // The format defines the format that the data type is stored in the DataStore
  private final boolean hasFormat;
  private final ColumnFormat format;

  // Indicates if the column is required or can it be null
  private final boolean isRequired;

  // Defined if a size is specified for the column. If not the default size is used
  private final boolean hasSize;
  private final int size;

  // Set of properties associated with the column
  private final Set<ColumnProperty> propertySet = new HashSet<ColumnProperty>();

  public ColumnSpec(ColumnDataType dataType, boolean isRequired, 
          ColumnFormat format, int size, Set<ColumnProperty> propertySet) {
    this.isRequired = isRequired;
    this.dataType = dataType;
    this.format = format;
    this.hasFormat = (format != null);
    this.size = size;
    this.hasSize = (size != Column.DEFAULT_SIZE);
    this.propertySet.addAll(propertySet);
  }

  public ColumnSpec(ColumnDataType dataType, boolean isRequired,
          ColumnProperty... properties) {
    this(dataType, isRequired, null, Column.DEFAULT_SIZE, convertToSet(properties));
  }

  public ColumnSpec(ColumnDataType dataType, ColumnFormat format,
      boolean isRequired, ColumnProperty... properties) {
    this(dataType, isRequired, format, Column.DEFAULT_SIZE, convertToSet(properties));
  }

  public ColumnSpec(ColumnDataType dataType, int size, boolean isRequired,
          ColumnProperty... properties) {
    this(dataType, isRequired, null, size, convertToSet(properties));
  }
  
  public ColumnSpec(ColumnDataType dataType, ColumnFormat format, 
      int size, boolean isRequired, ColumnProperty... properties) {
    this(dataType, isRequired, format, size, convertToSet(properties));
  }

  /**
   * @return The data type for the column
   */
  public final ColumnDataType getDataType() {
    return dataType;
  }
  
  /**
   * @return {@code true} if a special format is used to store data in the column. {@code false} if
   * default format is used.
   */
  public final boolean hasFormat() {
    return hasFormat;
  }

  /**
   * @return Special format, if any, that is used to store the data in the column. Returns null if
   * the default format is used.
   */
  public final ColumnFormat getFormat() {
    return format;
  }

  /**
   * @return {@code true} if the column is required and is not nullable, {@code false} otherwise.
   */
  public final boolean isRequired() {
    return isRequired;
  }  

  /**
   * @return {@code true} if a size is specified for the column, {@code false} if the default size
   * for the data type should be used for the column.
   */
  public final boolean hasSize() {
    return hasSize;
  }

  /**
   * @return integer size for the column. {@link TableColumnSpec#DEFAULT_SIZE} if the default size should be used.
   */
  public final int getSize() {
    return size;
  }

  /**
   * @return Set of properties for the column
   */
  public final Set<ColumnProperty> getProperties() {
    return propertySet;
  }    
  
  private static Set<ColumnProperty> convertToSet(ColumnProperty[] properties) {
    Set<ColumnProperty> propertySet = new HashSet<ColumnProperty>();
    if (properties != null) {
      for (ColumnProperty property : properties) {
        propertySet.add(property);
      }
    }
    return propertySet;
  }
}
