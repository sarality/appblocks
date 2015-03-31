package com.sarality.app.datastore;

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
   * @return Specification for the column
   */
  public ColumnSpec getSpec();

}
