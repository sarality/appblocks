package com.sarality.app.datastore.db.sqlgen;

import java.util.List;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.db.Table;
import com.sarality.app.datastore.db.TableInfo;

/**
 * Generates the SQL to create a database table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class CreateTableSQLGenerator implements SQLGenerator<Table<?>> {

  private final SQLGenerator<Column> columnSQLGenerator = new CreateTableColumnSQLGenerator();
  private final SQLGenerator<List<Column>> primaryKeySQLGenerator = new CompositePrimaryKeySQLGenerator();

  @Override
  public void appendSQL(StringBuilder builder, Table<?> table, TableInfo metadata) {
    // Start the SQL statement
    builder.append("CREATE TABLE ").append(table.getTableName()).append(" (\n");

    boolean hasCompositePrimaryKey = metadata.hasCompositePrimaryKey();
    // Add SQL for each column
    List<Column> columnList = table.getColumns();
    int lastIndex = columnList.size() - 1;
    for (int columnIndex = 0; columnIndex <= lastIndex; columnIndex++) {
      Column column = columnList.get(columnIndex);
      // Indent by 2 spaces for formatting
      builder.append("  ");
      columnSQLGenerator.appendSQL(builder, column, metadata);
      if (columnIndex < lastIndex) {
        builder.append(",\n");
      }
    }

    // Define composite Primary key here, if needed
    if (hasCompositePrimaryKey) {
      builder.append(",\n");
      // Indent by 2 spaces
      builder.append("  ");
      primaryKeySQLGenerator.appendSQL(builder, metadata.getPrimaryKeyColumns(), metadata);
    }

    // Close the SQL statement
    builder.append("\n);");
  }
}
