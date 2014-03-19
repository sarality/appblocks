package com.sarality.app.db.sqlgen;

import com.sarality.app.db.DatabaseTable;
import com.sarality.app.db.TableMetadata;

/**
 * Generates the SQL to drop a database table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DropTableSQLGenerator implements SQLGenerator<DatabaseTable<?>> {

  @Override
  public void appendSQL(StringBuilder builder, DatabaseTable<?> table, TableMetadata metadata) {
    builder.append("DROP TABLE ").append(table.getTableName()).append(";");    
  }
}
