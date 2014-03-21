package com.sarality.app.db;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.db.DatabaseColumn.Property;

/**
 * Metadata asscoaited with the Database table.
 * <p>
 * This data is used to both validate the table schema as well as create SQL for it
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class TableMetadata {

  /// Indicates whether the table used a Composite Primary Key i.e. a primary key 
  // composed of multiple columns
  private final boolean hasCompositePrimaryKey;
  
  // List of columns that form the primary key
  private final List<DatabaseColumn> primaryKeyColumns;

  // Should only be create by the class {@link DatabaseTable}.
  TableMetadata(List<DatabaseColumn> columns) {
    this.primaryKeyColumns = getPrimaryKeyColumns(columns);
    this.hasCompositePrimaryKey = primaryKeyColumns.size() > 1;
  }
  
  /*
   * @return true if table uses composite primary key, false otherwise.
   */
  public final boolean hasCompositePrimaryKey() {
    return hasCompositePrimaryKey;
  }

  /**
   * @return List of Columns that comprise the Primary Key
   */
  public final List<DatabaseColumn> getPrimaryKeyColumns() {
    return primaryKeyColumns;
  }

  /**
   * Extract all columns marked as Primary Key
   * 
   * @param columnList All columns of a database table.
   * @return List of columns that comprise the primary key
   */
  private static List<DatabaseColumn> getPrimaryKeyColumns(List<DatabaseColumn> columnList) {
    List<DatabaseColumn> primaryKeyColumns = new ArrayList<DatabaseColumn>();
    for (DatabaseColumn column : columnList) {
      if (column.getProperties().contains(Property.PRIMARY_KEY)) {
        primaryKeyColumns.add(column);
      }
    }
    return primaryKeyColumns;
  }  
}
