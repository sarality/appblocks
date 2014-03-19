package com.sarality.app.db.sqlgen;

import java.util.Set;

import com.sarality.app.db.Column;
import com.sarality.app.db.Column.DataType;
import com.sarality.app.db.TableMetadata;

/**
 * Generates the SQL to create an entry for a column, while generating the SQL to create a table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class CreateTableColumnSQLGenerator implements SQLGenerator<Column> {

  @Override
  public void appendSQL(StringBuilder builder, Column column, TableMetadata tableMetadata) {
    boolean hasCompositePrimaryKey = tableMetadata.hasCompositePrimaryKey();
    builder.append(column.getName()).append(" ").append(column.getDataType().name());
    Set<Column.Property> propertySet = column.getProperties();
    boolean isPrimaryKeyColumn = propertySet.contains(Column.Property.PRIMARY_KEY);

    if (isPrimaryKeyColumn) {
      if (hasCompositePrimaryKey) {
        // All columns of a composite primary key have to be declared as NOT NULL
        // since the composite key will be defined later
        builder.append(" NOT NULL");      
      } else {
        builder.append(" PRIMARY KEY");        
      }
    } 
    
    // Add NOT NULL for columns that are not part of the primary key
    if (!isPrimaryKeyColumn && column.isRequired()) {
        builder.append(" NOT NULL");        
    }
    
    // If the Primary Key column is marked with Auto Increment, make sure it is also an integer
    // and the table doesn't use a composite primary key
    if (propertySet.contains(Column.Property.AUTO_INCREMENT)) {
      if (!hasCompositePrimaryKey && column.getDataType() == DataType.INTEGER) {
        builder.append(" AUTOINCREMENT");
      }
    }
  }
}
