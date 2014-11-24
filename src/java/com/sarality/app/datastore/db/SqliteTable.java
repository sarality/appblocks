package com.sarality.app.datastore.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.AbstractWritableDataStore;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ContentValuesPopulator;
import com.sarality.app.datastore.CursorDataExtractor;
import com.sarality.app.datastore.query.Query;
import com.sarality.app.error.ValidationException;

/**
 * A table that store its data in a SqlLite database.
 * <p>
 * The class does most of the heavy lifting and leave the sub class the job of only defining the columns and the
 * marshaling/unmarshaling of data to and from the cursor.
 * 
 * @author abhideep@ (Abhideep Singh)
 * @param <T> Data associated with each row of the table.
 */
public abstract class SqliteTable<T extends DataObject<T>> extends AbstractWritableDataStore<T, Long> implements
    Table<T> {

  private static final Logger logger = LoggerFactory.getLogger(SqliteTable.class);

  // Name of the database e.g. users.db.
  private final String dbName;
  // Name of the database e.g. Users.
  private final String tableName;
  // The version of the database defined by the given set of columns
  private final int tableVersion;

  // TODO(abhideep): See if it makes sense to move version to column itself but first need to see how we update
  // the table to change an existing column size.

  // Metadata of the table, used mostly as a cache of properties of the table like whether it has a composite
  // primary key etc.
  private final TableInfo tableInfo;
  private final DataValidator<T> validator;
  private final DataSanitizer<T> sanitizer;

  // Utility class used to access the underlying database as well as manage the schema of the table.
  private final SqliteTableConnectionProvider dbProvider;

  // Reference of the underlying database instance that is used to query and update the data.
  private SQLiteDatabase database;
  private AtomicInteger dbOpenCounter = new AtomicInteger();

  protected TableListenerRegistryConfig<T> listenerRegistry = null;

  protected SqliteTable(Application application, String dbName, String tableName, int tableVersion,
      List<Column> columnList, CursorDataExtractor<T> extractor, ContentValuesPopulator<T> populator,
      SqliteTableSchemaUpdater schemaUpdter, DataValidator<T> validator, DataSanitizer<T> sanitizer) {
    super(tableName, columnList, extractor, populator);
    this.dbName = dbName;
    this.tableName = tableName;
    this.tableVersion = tableVersion;
    this.tableInfo = new TableInfo(columnList);
    this.validator = validator;
    this.sanitizer = sanitizer;
    this.dbProvider = new SqliteTableConnectionProvider(application.getApplicationContext(), this, null, schemaUpdter);
  }

  protected SqliteTable(Application application, String dbName, String tableName, int tableVersion,
                        List<Column> columnList, CursorDataExtractor<T> extractor, ContentValuesPopulator<T> populator,
                        SqliteTableSchemaUpdater schemaUpdter) {
    this(application, dbName, tableName,tableVersion, columnList, extractor, populator, schemaUpdter, null, null);
  }

  @Override
  public final String getDatabaseName() {
    return dbName;
  }

  @Override
  public final String getTableName() {
    return tableName;
  }

  @Override
  public final int getTableVersion() {
    return tableVersion;
  }

  @Override
  public final TableInfo getTableInfo() {
    return tableInfo;
  }

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
  @Override
  public synchronized final void open() throws SQLException {
    logger.debug("Opening database for Table {} ", getTableName());
    if (dbOpenCounter.incrementAndGet() == 1) {
      // This will automatically create or update the table as needed
      this.database = dbProvider.getWritableDatabase();
      logger.info("Opened database for Table {} Open Counter {}", getTableName(), dbOpenCounter.get());
    }
  }

  /**
   * Close the underlying database instance
   */
  @Override
  public synchronized final void close() {
    logger.debug("Closing database for Table {} ", getTableName());
    if (dbOpenCounter.decrementAndGet() == 0) {
      this.database.close();
      logger.info("Closed database for Table {} ", getTableName());
    }
  }

  protected void assertDatabaseOpen() {
    if (database == null) {
      throw new IllegalStateException(
          "Cannot perform operation since the database was either not opened or has already been closed.");
    }
  }

  protected T sanitize(T data) {
    if (sanitizer != null && data != null) {
      return sanitizer.sanitize(data);
    }
    return data;
  }

  protected boolean validate(T data) throws ValidationException {
    if (validator != null && data != null) {
      return validator.validate(data);
    }
    return true;
  }

  /**
   * Create a row in the database table.
   * 
   * @param data The data for the row that needs to be created.
   * @return The data for the row that was created with the appropriate id also populated.
   */
  @Override
  public Long create(T data) {
    logger.debug("Adding new row to table {} for data object {}", getTableName(), data);
    assertDatabaseOpen();

    // Before we create the data, sanitize and validate that the data is valid.
    T sanitizedData = sanitize(data);
    try {
      validate(sanitizedData);
    } catch (ValidationException e) {
      logger.error("Error Creating Row in Table {}. Invalid Data provided.", getTableName(), e);
      throw new IllegalArgumentException(e);
    }

    ContentValues contentValues = new ContentValues();
    if (getContentValuesPopulator().populate(contentValues, sanitizedData)) {
      logger.debug("Adding new row to table {} with Content Values {}", getTableName(), contentValues);

      // TODO(abhideep): Remove this whole Listener model.
      if (listenerRegistry != null)
        listenerRegistry.listener(sanitizedData, this);

      // TODO(abhideep): Call a method that converts a rowd Id to a Long
      return database.insert(getName(), null, contentValues);
    }
    return (long) -1;
  }

  /**
   * Delete the row or rows for the given query.
   * 
   * @param query Query to define the set of rows that need to be deleted.
   */
  @Override
  public void delete(Query query) {
    database.delete(getTableName(), query.getWhereClause(), query.getWhereClauseValues());
  }

  /**
   * Retrieve the set of rows from the table for the given query.
   * 
   * @param query Query to run on the table
   * @return List of data that was returned for the query
   */
  @Override
  public List<T> query(Query query) {
    Cursor cursor = null;

    if (query == null) {
      cursor = database.query(getName(), new String[] {}, null, null, null, null, null);
    } else {
      cursor = database.query(getName(), query.getColumns(), query.getWhereClause(), query.getWhereClauseValues(),
          null, null, query.getOrderBy());
    }

    CursorDataExtractor<T> extractor = getCursorDataExtractor();
    List<T> dataList = new ArrayList<T>();

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      T data = extractor.extract(cursor, query);
      dataList.add(data);
      cursor.moveToNext();
    }
    logger.debug("Query {} on table {} return {} values", query, getTableName(), dataList.size());
    // make sure to close the cursor
    cursor.close();
    return dataList;
  }

  @Override
  public void setListener(TableListenerRegistryConfig<T> listenerConfig) {
    this.listenerRegistry = listenerConfig;
  }

  /**
   * Update all rows that match the query with the data provided in the given data object.
   * 
   * @param data Data with the values that need to be updated.
   * @param query Query for the rows that need to be updated.
   */
  @Override
  public void update(T data, Query query) {
    assertDatabaseOpen();

    // Before we update the data, sanitize and validate that the data is valid.
    T sanitizedData = sanitize(data);
    try {
      validate(sanitizedData);
    } catch (ValidationException e) {
      logger.error("Error Updating Table {}. Invalid Data provided.", getTableName(), e);
      throw new IllegalArgumentException(e);
    }

    ContentValues contentValues = new ContentValues();
    getContentValuesPopulator().populate(contentValues, sanitizedData);
    int num = database.update(getName(), contentValues, query.getWhereClause(), query.getWhereClauseValues());
    logger.debug("Updated {} number of cols", num);
  }
}
