package com.sarality.app.data.field;

/**
 * Interface for all Fields in any data object.
 * <p>
 * Fields are generally implemented as an Enum which must implement this interface. This makes it easier to do certain
 * actions in a generic manner like reading or writing data from a DataObject.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface Field {

  /**
   * Data Type for the Field.
   */
  public enum DataType {
    BOOLEAN,
    DATE,
    DOUBLE,
    ENUM,
    INT,
    LONG,
    STRING,
    COMPLEX,
  }

  /**
   * @return String name for the field
   */
  public String getName();

  /**
   * @return DataType for the Field
   */
  public DataType getDataType();  
}
