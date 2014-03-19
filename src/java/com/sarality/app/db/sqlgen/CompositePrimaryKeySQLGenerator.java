package com.sarality.app.db.sqlgen;

import java.util.List;

import com.sarality.app.db.Column;
import com.sarality.app.db.TableMetadata;

/**
 * Generates the SQL for a Composite Primary Key.
 * <p>
 * Works for a single column Primary Key as well but should not be used
 * since the create table assume that a single column Primary Key is marked
 * explicitly
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class CompositePrimaryKeySQLGenerator implements SQLGenerator<List<Column>> {

  @Override
  public void appendSQL(StringBuilder builder, List<Column> columns, TableMetadata table) {
    boolean processedFirstColumn = false;
    builder.append("PRIMARY KEY (");
    // TODO(abhideep): Reorder columns based on Primary Key Column Order
    for (Column primaryKeyColumn : columns) {
      if (processedFirstColumn) {
        builder.append(", ");
      }
      builder.append(primaryKeyColumn.getName());
      processedFirstColumn = true;
    }
    builder.append(")");
  }
}
