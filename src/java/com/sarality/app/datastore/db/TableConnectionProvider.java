package com.sarality.app.datastore.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Provides a connection to the underlying database for a Table.
 * <p>
 * Manages the opening and closing of database instance as well as the schema of the database table.
 * <p>
 * Extends the SQLiteOpenHelper which is called automatically when the version of the database table
 * changes. This then updates the schema of the database.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class TableConnectionProvider extends SQLiteOpenHelper {

  private final Table<?> table;
  private final TableSchemaUpdater schemaUpdater;

  TableConnectionProvider(Context context, Table<?> table, CursorFactory factory,
      TableSchemaUpdater schemaUpdater) {
    super(context, table.getDbName(), factory, table.getTableVersion());
    this.table = table;
    if (schemaUpdater != null) {
      this.schemaUpdater = schemaUpdater;
    } else {
      this.schemaUpdater = new DefaultTableSchemaUpdater();
    }
  }

  /**
   * Create the Database Table
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    new DefaultTableSchemaUpdater(false).updateSchema(db, table);
  }

  /**
   * Upgrade the database table with the new schema for the table.
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    schemaUpdater.updateSchema(db, table);
  }
}
