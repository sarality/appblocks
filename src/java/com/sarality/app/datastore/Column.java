package com.sarality.app.datastore;

import java.util.LinkedHashSet;
import java.util.Set;

import com.sarality.app.datastore.db.Table;

/**
 * Interface for all Columns of a {@link Table}.
 * <p>
 * It is recommended that columns of a DatabaseTable be defined as an Enum that implements this
 * interface.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 */
public interface Column {

  // Default value for column data type size
  public static final int DEFAULT_SIZE = -1;
  
  /**
   * @return the Name for the Column
   */
  public String getName();

  /**
   * @return data type associated with the column
   */
  public DataType getDataType();

  /**
   * @return The format used by the column to store data
   */
  public DataTypeFormat getFormat();

  /**
   * @return The size of the data type to be used in the db. A {@link Column#DEFAULT_SIZE} represents the default size.
   */
  public int getSize();

  /**
   * @return true is column is NOT NULL, false otherwise
   */
  public boolean isRequired();

  /**
   * @return Set of properties associated with the column
   */
  public Set<Property> getProperties();

  /**
   * Enum for the formats supported by the various data types.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  public enum DataTypeFormat {
    // Store date as integer in format YYYYMMDDhhmmss
    DATE_AS_INT(DataType.INTEGER),
    // Store mapped value for enum
    ENUM_AS_INT(DataType.INTEGER),
    // Store mapped value for enum
    ENUM_AS_TXT(DataType.TEXT),
    // Store date as epoch i.e. milliseconds since January 1, 1970
    EPOCH(DataType.INTEGER);

    private DataType underlyingDataType;

    private DataTypeFormat(DataType underlyingDataType) {
      this.underlyingDataType = underlyingDataType;
    }

    public DataType getUnderlyingDataType() {
      return underlyingDataType;
    }
  }

  /**
   * Data types for database columns
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public enum DataType {
    INTEGER(null),
    TEXT(null),
    BOOLEAN(DataType.INTEGER),
    DATETIME(null, DataTypeFormat.DATE_AS_INT, DataTypeFormat.EPOCH),
    ENUM(DataType.TEXT, DataTypeFormat.ENUM_AS_INT, DataTypeFormat.ENUM_AS_TXT);

    private Set<DataTypeFormat> supportedFormatSet = new LinkedHashSet<DataTypeFormat>();
    private DataType underlyingDataType;

    private DataType(DataType underlyingDataType, DataTypeFormat... supportedFormats) {
      if (underlyingDataType == null) {
        this.underlyingDataType = this;
      } else {
        this.underlyingDataType = underlyingDataType;
      }
      for (DataTypeFormat format : supportedFormats) {
        supportedFormatSet.add(format);
      }
    }

    public boolean isSupportedFormat(DataTypeFormat format) {
      return supportedFormatSet.contains(format);
    }

    public DataType getUnderlyingDataType(DataTypeFormat format) {
      if (format == null) {
        return underlyingDataType;
      }
      return format.getUnderlyingDataType();
    }
  }

  /**
   * A property associated with the column.
   * <p>
   * This is a generic mechanism available to classes that extend Column to 
   * define properties that might be needed only by the sub classes or
   * classes that deal only with the sub class.
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public interface Property {

    /**
     * @return String name for the property.
     */
    public String getName();
    
    /**
     * @param property Property to compare against
     * @return {@code true} is the properties are teh same, {@code false} otherwise.
     */
    public boolean equals(Property property);
  }
}
