package com.sarality.app.datastore;

import java.util.List;

import android.content.Context;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.extractor.CursorDataExtractor;
import com.sarality.app.datastore.populator.ContentValuesPopulator;

/**
 * Abstract class that provides the base implementation for a writable version of {@link DataStore}, where items can
 * be updated or inserted.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The DataType returned by the DataStore.
 * @param <I> The type for the ID generated when creating an item.
 */
public abstract class AbstractWritableDataStore<T extends DataObject<T>, I> extends AbstractDataStore<T> 
    implements WritableDataStore<T, I> {

  private final ContentValuesPopulator<T> populator;

  /**
   * Constructor
   *
   * @param context The context passed to the data store to look up its application context.
   * @param columnList List of Column defined for the DataStore.
   * @param extractor Class that extracts the DataObject from the Cursor
   * @param populator Class that populates ContentValues used to create a new entry in the data store or update an existing one.
   */
  public AbstractWritableDataStore(Context context, List<Column> columnList, CursorDataExtractor<T> extractor,
          ContentValuesPopulator<T> populator) {
    super(context, columnList, extractor);
    this.populator = populator;
  }

  /**
   * @return Class that takes data from a {@code DataObject} and populates a {@code ContentValues} for
   * insertion or update of an item in the DataStore.
   */
  public final ContentValuesPopulator<T> getContentValuesPopulator() {
    return populator;
  }
}
