package com.sarality.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Manages the schema of the DatabaseTable as well as the opening and closing of database instance.
 * <p>
 * Extends the SQLiteOpenHelper which is called automatically when the version of the database table
 * changes.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class DatabaseTableManager extends SQLiteOpenHelper {

  private final DatabaseTable<?> table;
  private final TableSchemaUpdater schemaUpdater;

  DatabaseTableManager(Context context, DatabaseTable<?> table, CursorFactory factory,
      TableSchemaUpdater schemaUpdater) {
    super(context, table.getDbName(), factory, table.getTableVersion());
    this.table = table;
    if (schemaUpdater != null) {
      this.schemaUpdater = schemaUpdater;
    } else {
      this.schemaUpdater = new DefaultSchemaUpdater();
    }
  }

  /**
   * Create the Database Table
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    new DefaultSchemaUpdater(false).updateSchema(db, table);
  }

  /**
   * Upgrade the database table with the new schema for the table.
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    schemaUpdater.updateSchema(db, table);
  }
}
