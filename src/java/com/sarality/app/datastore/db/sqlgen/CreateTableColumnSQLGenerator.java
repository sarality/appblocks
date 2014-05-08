package com.sarality.app.datastore.db.sqlgen;

import java.util.Set;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnDataType;
import com.sarality.app.datastore.ColumnFormat;
import com.sarality.app.datastore.ColumnProperty;
import com.sarality.app.datastore.ColumnSpec;
import com.sarality.app.datastore.db.TableColumnProperty;
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
    ColumnSpec spec = column.getConfig().getSpec();
    ColumnFormat format = spec.getFormat();
    ColumnDataType dataType = spec.getDataType();
    builder.append(column.getName()).append(" ").append(dataType.getUnderlyingDataType(format));
    Set<ColumnProperty> propertySet = spec.getProperties();
    boolean isPrimaryKeyColumn = propertySet.contains(TableColumnProperty.PRIMARY_KEY);

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
    if (!isPrimaryKeyColumn && spec.isRequired()) {
        builder.append(" NOT NULL");        
    }

    // If the Primary Key column is marked with Auto Increment, make sure it is also an integer
    // and the table doesn't use a composite primary key
    if (propertySet.contains(TableColumnProperty.AUTO_INCREMENT)) {
      if (!hasCompositePrimaryKey && spec.getDataType() == ColumnDataType.INTEGER) {
        builder.append(" AUTOINCREMENT");
      }
    }
  }
}
