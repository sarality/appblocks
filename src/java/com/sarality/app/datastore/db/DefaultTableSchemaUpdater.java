package com.sarality.app.datastore.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sarality.app.datastore.db.sqlgen.CreateTableSQLGenerator;
import com.sarality.app.datastore.db.sqlgen.DropTableSQLGenerator;
import com.sarality.app.error.ValidationException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Default implementation of the {@link SqliteTableSchemaUpdater}
 * <p>
 * DROPS the table and then creates a new one. ALL EXISTING DATA IS LOST.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DefaultTableSchemaUpdater implements SqliteTableSchemaUpdater {

  private static final Logger logger = LoggerFactory.getLogger(DefaultTableSchemaUpdater.class);

  private final boolean dropTable;

  public DefaultTableSchemaUpdater() {
    this(true);
  }

  DefaultTableSchemaUpdater(boolean dropTable) {
    this.dropTable = dropTable;
  }

  @Override
  public void updateSchema(SQLiteDatabase db, Table<?> table) {
    TableInfo metadata = table.getTableInfo();

    // First Validate the Schema
    try {
      SqliteTableSchemaValidator validator = new SqliteTableSchemaValidator();
      validator.validate(table);
    } catch (ValidationException e) {
      logger.error("Invalid Schema for Table {} " + table.getTableName() + ". ", e);
      throw new SQLiteException(e.getMessage());
    }

    if (dropTable) {
      // Drop the Table
      StringBuilder dropTableSQLBuilder = new StringBuilder();
      new DropTableSQLGenerator().appendSQL(dropTableSQLBuilder, table, metadata);
      String dropTableSQL = dropTableSQLBuilder.toString();
      logger.info("Dropping table {} using the following SQL \n {} ", table.getTableName(), dropTableSQL);
      db.execSQL(dropTableSQL);
    }

    // Now Create the Table
    StringBuilder createTableSQLBuilder = new StringBuilder();
    new CreateTableSQLGenerator().appendSQL(createTableSQLBuilder, table, metadata);
    String createTableSQL = createTableSQLBuilder.toString();
    logger.info("Creating table {} using the following SQL \n {} ", table.getTableName(), createTableSQL);
    db.execSQL(createTableSQL);
  }
}
