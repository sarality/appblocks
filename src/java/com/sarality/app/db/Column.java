package com.sarality.app.db;

import java.util.Set;
/**
 * Interface for all Columns of a {@link DatabaseTable}.
 * <p>
 * It is recommended that columns of a DatabaseTable be defined as an Enum that implements this
 * interace.
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
   * @return true is column is NOT NULL, false otherwise
   */
  public boolean isRequired();

  /**
   * @return Set of properties associated with the column
   */
  public Set<Property> getProperties();

  /**
   * SQL data types for database columns
   * 
   * @author abhideep@ (Abhideep Singh)
   */
  public enum DataType {
    INTEGER,
    TEXT
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
