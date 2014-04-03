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
    // Store date as integer in format YYYYMMDDhhmmss
    DATE_AS_INT(DataType.INTEGER),
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
    INTEGER(),
    TEXT(),
    DATETIME(DataTypeFormat.DATE_AS_INT, DataTypeFormat.EPOCH);
    
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
