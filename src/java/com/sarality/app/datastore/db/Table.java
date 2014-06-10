package com.sarality.app.datastore.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.AbstractWritableDataStore;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.Query;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.populator.ContentValuesPopulator;

/**
 * Provides the ability to create and update a SqlLite table and then read and
 * write data for the table.
 * <p>
 * The class does most of the heavy lifting and leave the sub class the job of
 * only defining the columns and the marshaling/unmarshaling of data to and from
 * the cursor
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T>
 *          Data associated with each row of the table
 */
public abstract class Table<T extends DataObject<T>> extends AbstractWritableDataStore<T, Long> {

  // Name of the database e.g. users.db.
  private final String dbName;
  // The version of the database defined by the given set of columns
  private final int tableVersion;
  // TODO(abhideep): See if it makes sense to move version to column itself
  // but first need to see how we update the table to change an existing column
  // size.

  // Metadata of the table, used mostly as a cache of properties of the table
  // like whether
  // it has a composite primary key etc.
  private final TableInfo tableInfo;

  // Utility class used to access the underlying database as well as manage the
  // schema of the table.
  private final TableConnectionProvider dbProvider;
  // Reference of the underlying database instance that is used to query and
  // update the data.
  private SQLiteDatabase database;
  private AtomicInteger dbOpenCounter = new AtomicInteger();

  protected TableListenerRegistryConfig<T> listenerRegistry = null;

  protected Table(Application application, String dbName, String tableName, int tableVersion, List<Column> columnList,
      CursorDataExtractor<T> extractor, ContentValuesPopulator<T> populator, TableSchemaUpdater schemaUpdter) {
    super(application.getApplicationContext(), tableName, columnList, extractor, populator);
    this.dbName = dbName;
    this.tableVersion = tableVersion;
    this.tableInfo = new TableInfo(columnList);
    this.dbProvider = new TableConnectionProvider(application.getApplicationContext(), this, null, schemaUpdter);
  }

  public final String getDbName() {
    return dbName;
  }

  public final String getTableName() {
    return getName();
  }

  public final int getTableVersion() {
    return tableVersion;
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
   * @throws SQLException
   *           if there is an error opening the database for writing.
   */
  public synchronized final void open() throws SQLException {
    Log.d(getLoggerTag(), "Opening database for Table " + getName());
    if (dbOpenCounter.incrementAndGet() == 1) {
      // This will automatically create or update the table as needed
      this.database = dbProvider.getWritableDatabase();
      Log.i(getLoggerTag(), "Opened database for Table " + getName() + " Open Counter " + dbOpenCounter.get());
    }
  }

  /**
   * Close the underlying database instance
   */
  public synchronized final void close() {
    Log.d(getLoggerTag(), "Closing database for Table " + getName());
    if (dbOpenCounter.decrementAndGet() == 0) {
      this.database.close();
      Log.i(getLoggerTag(), "Closed database for Table " + getName());
    }
  }

  protected void assertDatabaseOpen() {
    if (database == null) {
      throw new IllegalStateException(
          "Cannot perform operation since the database was either not opened or has already been closed.");
    }
  }

  /**
   * Create a row in the database table.
   * 
   * @param data
   *          The data for the row that needs to be created.
   * @return The data for the row that was created with the appropriate id also
   *         populated.
   */
  public Long create(T data) {
    Log.d(getLoggerTag(), "Adding new row to table " + getName() + " for data object " + data);
    assertDatabaseOpen();
    ContentValues contentValues = new ContentValues();
    getContentValuesPopulator().populate(contentValues, data);
    Log.d(getLoggerTag(), "Adding new row to table " + getName() + " with content values " + contentValues);

    if (listenerRegistry != null)
      listenerRegistry.listener(data, this);

    // TODO(abhideep): Call a method that converts a rowd Id to a Long
    return database.insert(getName(), null, contentValues);
  }

  /**
   * Delete the row or rows for the given query.
   * 
   * @param query
   *          Query to define the set of rows that need to be deleted.
   */
  public void delete(Query query) {
    // TODO(abhideep): Needs implementation
  }

  /**
   * Retrieve the set of rows from the table for the given query.
   * 
   * @param query
   *          Query to run on the table
   * @return List of data that was returned for the query
   */
  public List<T> query(Query query) {
    Cursor cursor = database.query(getName(), new String[] {}, null, null, null, null, null);
    CursorDataExtractor<T> extractor = getCursorDataExtractor();
    List<T> dataList = new ArrayList<T>();

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      T data = extractor.extract(cursor, query);
      dataList.add(data);
      cursor.moveToNext();
    }
    Log.d(getLoggerTag(), "Query " + query + " on table " + getName() + " returned " + dataList.size() + " values");
    // make sure to close the cursor
    cursor.close();
    return dataList;
  }

  public void setListener(TableListenerRegistryConfig<T> listenerConfig) {
    this.listenerRegistry = listenerConfig;
  }

  public void update(T data, Query query) {
    assertDatabaseOpen();
    ContentValues contentValues = new ContentValues();
    getContentValuesPopulator().populate(contentValues, data);
    int num = database.update(getName(), contentValues, query.getWhereClause(), query.getWhereClauseValues());
    Log.d(getLoggerTag(), "Updated " + num + "number of cols");
  }

}
