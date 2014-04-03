package com.sarality.app.datastore.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.Query;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.populator.ContentValuesPopulator;

/**
 * Provides the ability to create and update a SqlLite table and then read and write
 * data for the table.
 * <p>
 * The class does most of the heavy lifting and leave the sub class the job of only defining the
 * columns and the marshaling/unmarshaling of data to and from the cursor 
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Data associated with each row of the table
 */
public abstract class Table<T> {
  
  // Name of the database e.g. users.db.
  private final String dbName;
  // Name of table in this database e.g. users.
  private final String tableName;  
  // The version of the database defined by the given set of columns
  private final int tableVersion;
  // TODO(abhideep): See if it makes sense to move version to column itself
  // but first need to see how we update the table to change an existing column size.

  // The columns of the database table.
  private final List<Column> columnList;
  // Metadata of the table, used mostly as a cache of properties of the table like whether
  // it has a composite primary key etc.
  private final TableInfo tableInfo;
  
  private final CursorDataExtractor<T> extractor;
  private final ContentValuesPopulator<T> populator;

  // Utility class used to access the underlying database as well as manage the schema of the table.
  private final TableConnectionProvider dbProvider;
  // Reference of the underlying database instance that is used to query and update the data.
  private SQLiteDatabase database;
  private AtomicInteger dbOpenCounter = new AtomicInteger();

  protected Table(Context context, String dbName, String tableName, int tableVersion,
          List<? extends Column> columnList, CursorDataExtractor<T> extractor, ContentValuesPopulator<T> populator,
          TableSchemaUpdater schemaUpdter) {
    this.dbName = dbName;
    this.tableName = tableName;
    this.tableVersion = tableVersion;
    this.columnList = new ArrayList<Column>();
    this.columnList.addAll(columnList);
    
    this.extractor = extractor;
    this.populator = populator;

    this.tableInfo = new TableInfo(this.columnList);    
    this.dbProvider = new TableConnectionProvider(context, this, null, schemaUpdter); 
  }

  public final String getDbName() {
    return dbName;
  }
  
  public final String getTableName() {
    return tableName;
  }
  
  public final int getTableVersion() {
    return tableVersion;
  }

  public final List<Column> getColumns() {
    return columnList;
  }

  public final TableInfo getTableInfo() {
    return tableInfo;
  }

  public abstract String getLoggerTag();

  /**
   * Returns the order of the columns in the primary key.
   * <p>
   * NOTE: By default, no order of the columns is defined. As a result the .
   * 
   * @return Ordered list if Columns that form the primary key for the table.
   */
  protected List<Column> getPrimaryKeyColumnOrder() {
    return null;
  }

  /**
   * Open a writable instance of the database.
   * 
   * @throws SQLException if there is an error opening the database for writing.
   */
  public synchronized final void open() throws SQLException {
    Log.d(getLoggerTag(), "Opening database for Table " + tableName);
    if (dbOpenCounter.incrementAndGet() == 1) {
      // This will automatically create or update the table as needed
      this.database = dbProvider.getWritableDatabase();
      Log.i(getLoggerTag(), "Opened database for Table " + tableName + " Open Counter " + dbOpenCounter.get());
    }
  }

  /**
   * Close the underlying database instance
   */
  public synchronized final void close() {
    Log.d(getLoggerTag(), "Closing database for Table " + tableName);
    if (dbOpenCounter.decrementAndGet() == 0) {
      this.database.close();
      Log.i(getLoggerTag(), "Closed database for Table " + tableName);
    }
  }

  /**
   * @return
   */
  public final CursorDataExtractor<T> getCursorDataExtractor() {
    return extractor;
  }

  /**
   * @return
   */
  public final ContentValuesPopulator<T> getContentValuesPopulator() {
    return populator;
  }

  protected void assertDatabaseOpen() {
    if (database == null) {
      throw new IllegalStateException("Cannot perform operation since the database was either not opened or has already been closed.");
    }
  }

  /**
   * Create a row in the database table.
   * 
   * @param data The data for the row that needs to be created.
   * @return The data for the row that was created with the appropriate id also populated.
   */
  public long create(T data) {
    Log.d(getLoggerTag(), "Adding new row to table " + tableName + " for data object " + data);
    assertDatabaseOpen();
    ContentValues contentValues = new ContentValues();
    getContentValuesPopulator().populate(contentValues, data);
    Log.d(getLoggerTag(), "Adding new row to table " + tableName + " with content values " + contentValues);
    return database.insert(tableName, null, contentValues);
  }

  /**
   * Delete the row or rows for the given query.
   * 
   * @param query Query to define the set of rows that need to be deleted.
   */
  public void delete(Query query) {
    // TODO(abhideep): Needs implementation
  }

  /**
   * Retrieve the set of rows from the table for the given query.
   * 
   * @param query Query to run on the table
   * @return List of data that was returned for the query
   */
  public List<T> query(Query query) {
    // TODO(abhideep): Needs implementation
    Cursor cursor = database.query(tableName, new String[] {}, null, null, null, null, null);
    CursorDataExtractor<T> extractor = getCursorDataExtractor();
    List<T> dataList = new ArrayList<T>();

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      T data = extractor.extract(cursor, query);
      dataList.add(data);
      cursor.moveToNext();
    }
    Log.d(getLoggerTag(), "Query " + query + " on table " + tableName + " returned " + dataList.size() + " values");
    // make sure to close the cursor
    cursor.close();
    return dataList;
  }
}
