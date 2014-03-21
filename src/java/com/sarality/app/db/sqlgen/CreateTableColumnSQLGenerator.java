package com.sarality.app.db.sqlgen;

import java.util.Set;

import com.sarality.app.db.DatabaseColumn;
import com.sarality.app.db.DatabaseColumn.DataType;
import com.sarality.app.db.TableMetadata;

/**
 * Generates the SQL to create an entry for a column, while generating the SQL to create a table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class CreateTableColumnSQLGenerator implements SQLGenerator<DatabaseColumn> {

  @Override
  public void appendSQL(StringBuilder builder, DatabaseColumn column, TableMetadata tableMetadata) {
    boolean hasCompositePrimaryKey = tableMetadata.hasCompositePrimaryKey();
    DatabaseColumn.DataTypeFormat format = column.getFormat();
    DatabaseColumn.DataType dataType = column.getDataType();
    builder.append(column.getName()).append(" ").append(dataType.getUnderlyingDataType(format));
    Set<DatabaseColumn.Property> propertySet = column.getProperties();
    boolean isPrimaryKeyColumn = propertySet.contains(DatabaseColumn.Property.PRIMARY_KEY);

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
    if (propertySet.contains(DatabaseColumn.Property.AUTO_INCREMENT)) {
      if (!hasCompositePrimaryKey && column.getDataType() == DataType.INTEGER) {
        builder.append(" AUTOINCREMENT");
      }
    }
  }
}
