package com.sarality.app.datastore.extractor;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.Query;

import android.database.Cursor;

/**
 * Interface for all classes that extract a data object from a database cursor.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of data to extract
 */
public interface CursorDataExtractor<T extends DataObject<T>> {

  /**
   * Extract an object of the given type from the cursor that was returned by running the given query.
   * @param cursor The cursor to extract data from
   * @param query The query that generated this cursor
   * @return The extracted Data Object.
   */
  public T extract(Cursor cursor, Query query);
}
