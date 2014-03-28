package com.sarality.app.db;

import java.util.LinkedHashSet;
import java.util.Set;
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

  /**
   * @return the SQL Name for the Column
   */
  public String getName();

  /**
   * @return SQL data type associated with the column
   */
  public DataType getDataType();

  /**
   * @return The format used by the column to store data
   */
  public DataTypeFormat getFormat();

  /**
   * @return The size of the data type to be used in the db. A -1 represents the default size.
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

  public enum DataTypeFormat {
    YYYYMMDDHHSS(DataType.INTEGER),
    EPOCH(DataType.INTEGER);
    
    private DataType underlyingDataType;
    private DataTypeFormat(DataType underlyingDataType) {
      this.underlyingDataType = underlyingDataType;
    }
    DataType getUnderlyingDataType() {
      return underlyingDataType;
    }
  }

  /**
   * Data types for database columns
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public enum DataType {
    INTEGER(),
    TEXT(),
    DATETIME(DataTypeFormat.YYYYMMDDHHSS, DataTypeFormat.EPOCH);
    
    private Set<DataTypeFormat> supportedFormatSet = new LinkedHashSet<DataTypeFormat>();

    private DataType(DataTypeFormat... supportedFormats) {
      for (DataTypeFormat format : supportedFormats) {
        supportedFormatSet.add(format);
      }
    }

    public boolean isSupportedFormat(DataTypeFormat format) {
      return supportedFormatSet.contains(format);
    }

    public DataType getUnderlyingDataType(DataTypeFormat format) {
      if (format == null) {
        return this;
      }
      return format.getUnderlyingDataType();
    }
  }

  /**
   * Properties associated with the database columns
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public enum Property {
    AUTO_INCREMENT,
    PRIMARY_KEY,
  }
}
