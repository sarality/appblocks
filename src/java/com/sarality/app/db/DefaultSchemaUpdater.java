package com.sarality.app.db;
/**
 */
import com.sarality.app.db.sqlgen.CreateTableSQLGenerator;
import com.sarality.app.db.sqlgen.DropTableSQLGenerator;
import com.sarality.app.error.ValidationException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Default implementation of the {@link TableSchemaUpdater}
 * <p>
 * DROPS the table and then creates a new one. ALL EXISTING DATA IS LOST.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DefaultSchemaUpdater implements TableSchemaUpdater {
  private static final String TAG = "DefaultTableSchemaUpdater";

  private final boolean dropTable;

  public DefaultSchemaUpdater() {
    this(true);
  }

  DefaultSchemaUpdater(boolean dropTable) {
    this.dropTable = dropTable;
  }

  @Override
  public void updateSchema(SQLiteDatabase db, DatabaseTable<?> table) {
    TableMetadata metadata = table.getMetadata();

    // First Validate the Schema
    try {
      TableSchemaValidator validator = new TableSchemaValidator();
      validator.validate(table);
    } catch (ValidationException e) {
      Log.e(TAG, "Invalid Schema for Table " + table.getTableName() + ". " + e.getMessage());
      throw new SQLiteException(e.getMessage());
    }

    if (dropTable) {
      // Drop the Table
      StringBuilder dropTableSQLBuilder = new StringBuilder();
      new DropTableSQLGenerator().appendSQL(dropTableSQLBuilder, table, metadata);
      String dropTableSQL = dropTableSQLBuilder.toString();
      Log.i(TAG, "Dropping table " + table.getTableName() + " using the following SQL \n"
          + dropTableSQL);
      db.execSQL(dropTableSQL);
    }

    // Now Create the Table
    StringBuilder createTableSQLBuilder = new StringBuilder();
    new CreateTableSQLGenerator().appendSQL(createTableSQLBuilder, table, metadata);
    String createTableSQL = createTableSQLBuilder.toString();
    Log.i(TAG, "Creating table " + table.getTableName() + " using the following SQL \n"
        + createTableSQL);
    db.execSQL(createTableSQL);
  }
}
