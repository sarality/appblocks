package com.sarality.app.datastore;

import java.util.List;

import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.query.Query;

/**
 * Interface for all classes that implement the ability to fetch DataObjects from a store
 * defined by a set of Columns.
 * <p>
 * 
 * {@see WritableDatastore, AbstractDatastore, AbstractWritableDatastore}
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of DataObject return the by the DataStore.
 */
public interface DataStore<T> {

  /**
   * @return String name for the data store.
   */
  public String getName();

  /**
   * Open a DataStore for further operations
   */
  public void open();

  /**
   * Close the DataStore and perform necessary cleanups
   */
  public void close();

  /**
   * @return {@code List} of {@code Column}s defined for the DataStore.
   */
  public List<Column> getColumns();

  /**
   * Returns a {@code List} of {@code DataObject}s that match the given query.
   * 
   * @param query The query to run on the DataStore.
   * @return  {@code List} of {@code DataObject}s matching the query.
   */
  public List<T> query(Query query);

  /**
   * @return The class that convert the cursor returned by the DataStore into a DataObject.
   */
  public CursorDataExtractor<T> getCursorDataExtractor();
}
