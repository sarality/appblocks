package com.sarality.app.datastore;

import java.util.HashSet;
import java.util.Set;

public class ColumnSpec {

  // The data type for the column
  private final Column.DataType dataType;
  // Data types like date can be saved in multiple formats like EPoch or as integers.
  // The format defines the format that the data type is stored in the DataStore
  private final boolean hasFormat;
  private final Column.DataTypeFormat format;

  // Indicates if the column is required or can it be null
  private final boolean isRequired;

    // Defined if a size is specified for the column. If not the default size is used
  private final boolean hasSize;
  private final int size;

  // Set of properties associated with the column
  private final Set<Column.Property> propertySet = new HashSet<Column.Property>();

  private ColumnSpec(Column.DataType dataType, boolean isRequired, 
      Column.DataTypeFormat format, int size,
      Set<Column.Property> propertySet) {
    this.isRequired = isRequired;
    this.dataType = dataType;
    this.format = format;
    this.hasFormat = (format != null);
    this.size = size;
    this.hasSize = (size != Column.DEFAULT_SIZE);
    this.propertySet.addAll(propertySet);
  }

  public ColumnSpec(Column.DataType dataType, boolean isRequired,
      Column.Property... properties) {
    this(dataType, isRequired, null, Column.DEFAULT_SIZE, convertToSet(properties));
  }

  public ColumnSpec(Column.DataType dataType, Column.DataTypeFormat format,
      boolean isRequired, Column.Property... properties) {
    this(dataType, isRequired, format, Column.DEFAULT_SIZE, convertToSet(properties));
  }

  public ColumnSpec(Column.DataType dataType, int size, boolean isRequired,
      Column.Property... properties) {
    this(dataType, isRequired, null, size, convertToSet(properties));
  }
  
  public ColumnSpec(Column.DataType dataType, Column.DataTypeFormat format, 
      int size, boolean isRequired, Column.Property... properties) {
    this(dataType, isRequired, format, size, convertToSet(properties));
  }

  /**
   * @return The data type for the column
   */
  public final Column.DataType getDataType() {
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
  public final Column.DataTypeFormat getFormat() {
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
  public final Set<Column.Property> getProperties() {
    return propertySet;
  }    
  
  private static Set<Column.Property> convertToSet(Column.Property[] properties) {
    Set<Column.Property> propertySet = new HashSet<Column.Property>();
    if (properties != null) {
      for (Column.Property property : properties) {
        propertySet.add(property);
      }
    }
    return propertySet;
  }
}
