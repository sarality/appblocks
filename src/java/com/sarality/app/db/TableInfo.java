package com.sarality.app.db;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.db.Column.Property;

/**
 * Information about a Database table.
 * <p>
 * This data is used to both validate the table schema as well as create SQL for it
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class TableInfo {

  /// Indicates whether the table used a Composite Primary Key i.e. a primary key 
  // composed of multiple columns
  private final boolean hasCompositePrimaryKey;

  // List of columns that form the primary key
  private final List<Column> primaryKeyColumns;

  // Should only be create by the class {@link DatabaseTable}.
  TableInfo(List<Column> columns) {
    this.primaryKeyColumns = getPrimaryKeyColumns(columns);
    this.hasCompositePrimaryKey = primaryKeyColumns.size() > 1;
  }
  
  /**
   * 
   * @return {@code true}, if table uses composite primary key, {@code false{ otherwise.
   */
  public final boolean hasCompositePrimaryKey() {
    return hasCompositePrimaryKey;
  }

  /**
   * @return List of Columns that comprise the Primary Key.
   */
  public final List<Column> getPrimaryKeyColumns() {
    return primaryKeyColumns;
  }

  /**
   * Extract all columns marked as Primary Key
   * 
   * @param columnList All columns of a database table.
   * @return List of columns that comprise the primary key
   */
  private static List<Column> getPrimaryKeyColumns(List<Column> columnList) {
    List<Column> primaryKeyColumns = new ArrayList<Column>();
    for (Column column : columnList) {
      if (column.getProperties().contains(Property.PRIMARY_KEY)) {
        primaryKeyColumns.add(column);
      }
    }
    return primaryKeyColumns;
  }  
}
