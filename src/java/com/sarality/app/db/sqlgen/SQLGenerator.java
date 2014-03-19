package com.sarality.app.db.sqlgen;

import com.sarality.app.db.TableMetadata;

/**
 * Interface for all classes that create SQL for creation and update of SQLite database schema
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The data of the table, column or index that is SQL is being generated for.
 */
public interface SQLGenerator<T> {

  /**
   * Append SQL to the StringBuilder that contains the SQL so far.
   * 
   * @param builder StringBuilder to append SQL to
   * @param data The data of the table, column or index that the SQL is being generated for
   * @param table The table for which the SQL is being generated
   */
  public void appendSQL(StringBuilder builder, T data, TableMetadata table);
}
