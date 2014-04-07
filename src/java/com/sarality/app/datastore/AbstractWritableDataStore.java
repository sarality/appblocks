package com.sarality.app.datastore;

import java.util.List;

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

  public AbstractWritableDataStore(List<Column> columnList, CursorDataExtractor<T> extractor,
          ContentValuesPopulator<T> populator) {
    super(columnList, extractor);
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
