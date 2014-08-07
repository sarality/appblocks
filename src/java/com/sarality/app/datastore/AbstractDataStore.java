package com.sarality.app.datastore;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.datastore.extractor.CursorDataExtractor;

/**
 * Abstract class that provides the base implementation for all {@link DataStore}s.
 * <p>
 * The implementation classes simply needs to define the {@link Column}s in the DataStore as well as a class to read
 * data from a Cursor into a DataObject. The rest is handled by this class.
 * 
 * @author abhideep@ (Abhideep Singh)
 * @param <T> The type of DataObject returned by the DataStore.
 */
public abstract class AbstractDataStore<T> implements DataStore<T> {

  // The name of the data store
  private final String name;

  // The columns of the database table.
  private final List<Column> columnList;

  // Extracts the data from the Cursor and puts it in a DataObject.
  private final CursorDataExtractor<T> extractor;

  /**
   * Constructor
   * 
   * @param columnList List of Column defined for the DataStore.
   * @param extractor Class that extracts the DataObject from the Cursor
   */
  public AbstractDataStore(String name, List<Column> columnList, CursorDataExtractor<T> extractor) {
    this.name = name;
    this.columnList = new ArrayList<Column>();
    this.columnList.addAll(columnList);
    this.extractor = extractor;
  }

  @Override
  public final String getName() {
    return name;
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
