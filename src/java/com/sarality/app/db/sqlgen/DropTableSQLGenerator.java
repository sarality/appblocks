package com.sarality.app.db.sqlgen;

import com.sarality.app.db.Table;
import com.sarality.app.db.TableInfo;

/**
 * Generates the SQL to drop a database table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DropTableSQLGenerator implements SQLGenerator<Table<?>> {

  @Override
  public void appendSQL(StringBuilder builder, Table<?> table, TableInfo metadata) {
    builder.append("DROP TABLE ").append(table.getTableName()).append(";");    
  }
}
