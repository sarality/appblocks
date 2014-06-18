package com.sarality.app.datastore;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.populator.ContentValuesPopulator;
import com.sarality.app.datastore.query.Query;

/**
 * An interface for all classes that can not only read by insert and update items in a {@code DataStore}.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of {@link DataObject} needed to create the item in the store.
 * @param <I> The type of ID returned when creating the item in the store.
 */
public interface WritableDataStore<T extends DataObject<T>, I> extends DataStore<T> {

  /**
   * Create an item in the {@code DataStore}.
   *
   * @param data The data for the item that needs to be created.
   * @return The id for the item that was created.
   */
  public I create(T data);

  /**
   * Delete the item(s) for the given query.
   * 
   * @param query Query to define the set of items that need to be deleted.
   */
  public void delete(Query query);

  /**
   * @return A class that populates values in the {@code ContentValues} needed to insert
   * or update data in a data store.
   */
  public ContentValuesPopulator<T> getContentValuesPopulator();
}
