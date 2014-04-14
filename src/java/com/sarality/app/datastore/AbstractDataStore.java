package com.sarality.app.datastore;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.extractor.CursorDataExtractor;

/**
 * Abstract class that provides the base implementation for all {@link DataStore}s.
 * <p>
 * The implementation classes simply needs to define the {@link Column}s in the DataStore as well
 * as a class to read data from a Cursor into a DataObject. The rest is handled by this class.
 *
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of DataObject returned by the DataStore.
 */
public abstract class AbstractDataStore<T extends DataObject<T>> implements DataStore<T> {

  // The application context for the data store. 
  private final Context applicationContext;

  // The columns of the database table.
  private final List<Column> columnList;

  // Extracts the data from the Cursor and puts it in a DataObject.
  private final CursorDataExtractor<T> extractor;

  /**
   * Constructor
   *
   * @param context The context passed to the data store to look up its application context.
   * @param columnList List of Column defined for the DataStore.
   * @param extractor Class that extracts the DataObject from the Cursor
   */
  public AbstractDataStore(Context context, List<Column> columnList, CursorDataExtractor<T> extractor) {
    this.applicationContext = context.getApplicationContext();
    this.columnList = new ArrayList<Column>();
    this.columnList.addAll(columnList);

    this.extractor = extractor;
  }

  /**
   * @return The String TAG to be used to logging messages.
   */
  public abstract String getLoggerTag();

  @Override
  public final Context getApplicationContext() {
    return applicationContext;
  }

  @Override
  public final List<Column> getColumns() {
    return columnList;
  }

  @Override
  public final CursorDataExtractor<T> getCursorDataExtractor() {
    return extractor;
  }
  
  @Override
  public void open() {
    // No-op: Most data stores don't require any open or close calls
  }

  @Override
  public void close() {
    // No-op: Most data stores don't require any open or close calls
  }
}
