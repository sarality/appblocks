package com.sarality.app.db.sqlgen;

import java.util.List;

import com.sarality.app.db.DatabaseColumn;
import com.sarality.app.db.DatabaseTable;
import com.sarality.app.db.TableMetadata;

/**
 * Generates the SQL to create a database table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class CreateTableSQLGenerator implements SQLGenerator<DatabaseTable<?>> {

  private final SQLGenerator<DatabaseColumn> columnSQLGenerator = new CreateTableColumnSQLGenerator();
  private final SQLGenerator<List<DatabaseColumn>> primaryKeySQLGenerator = new CompositePrimaryKeySQLGenerator();

  @Override
  public void appendSQL(StringBuilder builder, DatabaseTable<?> table, TableMetadata metadata) {
    // Start the SQL statement
    builder.append("CREATE TABLE ").append(table.getTableName()).append(" (\n");

    boolean hasCompositePrimaryKey = metadata.hasCompositePrimaryKey();
    // Add SQL for each column
    List<DatabaseColumn> columnList = table.getColumns();
    int lastIndex = columnList.size() - 1;
    for (int columnIndex = 0; columnIndex <= lastIndex; columnIndex++) {
      DatabaseColumn column = columnList.get(columnIndex);
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
