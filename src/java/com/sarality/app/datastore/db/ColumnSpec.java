package com.sarality.app.datastore.db;

import java.util.HashSet;
import java.util.Set;

import com.sarality.app.datastore.Column;

/**
 * Specification / Definition for a {@link Column}.
 * <p>
 * Class is used by subclasses of {@link Datastore} to define the columns in the store.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class ColumnSpec {

  // Default value for column data type size
  public static final int DEFAULT_SIZE = -1;

  // Indicates if the column is required or can it be null
  private final boolean isRequired;
  // The data type for the column
  private final Column.DataType dataType;
  // Data types like date can be saved in multiple formats like EPoch or as integers.
  // The format defines the format that the data type is stored in the datastore
  private final boolean hasFormat;
  private final Column.DataTypeFormat format;
  
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
    this.hasSize = (size != DEFAULT_SIZE);
    this.propertySet.addAll(propertySet);
  }

  public ColumnSpec(Column.DataType dataType, boolean isRequired,
      Column.Property... properties) {
    this(dataType, isRequired, null, DEFAULT_SIZE, convertToSet(properties));
  }

  public ColumnSpec(Column.DataType dataType, Column.DataTypeFormat format,
      boolean isRequired, Column.Property... properties) {
    this(dataType, isRequired, format, DEFAULT_SIZE, convertToSet(properties));
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
   * @return {@code true} if a size is specified for the column, {@code false} if the default size
   * for the data type should be used for the column.
   */
  public final boolean hasSize() {
    return hasSize;
  }

  /**
   * @return integer size for the column. {@link ColumnSpec#DEFAULT_SIZE} if the default size should be used.
   */
  public final int getSize() {
    return size;
  }

  /**
   * @return {@code true} if the column is required and is not nullable, {@code false} otherwise.
   */
  public final boolean isRequired() {
    return isRequired;
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
