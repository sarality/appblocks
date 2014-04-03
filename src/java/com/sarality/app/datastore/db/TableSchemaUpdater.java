package com.sarality.app.datastore.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Interface for all classes that update / upgrade the schema for a table.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface TableSchemaUpdater {

  /**
   * Update the schema for the given table in the given database
   * 
   * @param db The database to be updated
   * @param table The table that needs to be updated with its current schema
   */
  public void updateSchema(SQLiteDatabase db, Table<?> table);
}
