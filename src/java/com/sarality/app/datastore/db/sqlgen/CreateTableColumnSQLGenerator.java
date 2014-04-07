package com.sarality.app.datastore.db.sqlgen;

import java.util.Set;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.Column.DataType;
import com.sarality.app.datastore.db.TableColumn;
import com.sarality.app.datastore.db.TableInfo;

/**
 * Generates the SQL to create an entry for a column, while generating the SQL to create a table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class CreateTableColumnSQLGenerator implements SQLGenerator<Column> {

  @Override
  public void appendSQL(StringBuilder builder, Column column, TableInfo tableMetadata) {
    boolean hasCompositePrimaryKey = tableMetadata.hasCompositePrimaryKey();
    Column.DataTypeFormat format = column.getFormat();
    Column.DataType dataType = column.getDataType();
    builder.append(column.getName()).append(" ").append(dataType.getUnderlyingDataType(format));
    Set<Column.Property> propertySet = column.getProperties();
    boolean isPrimaryKeyColumn = propertySet.contains(TableColumn.Property.PRIMARY_KEY);

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
    if (propertySet.contains(TableColumn.Property.AUTO_INCREMENT)) {
      if (!hasCompositePrimaryKey && column.getDataType() == DataType.INTEGER) {
        builder.append(" AUTOINCREMENT");
      }
    }
  }
}
