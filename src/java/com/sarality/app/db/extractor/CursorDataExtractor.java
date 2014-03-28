package com.sarality.app.db.extractor;

import com.sarality.app.db.Query;

import android.database.Cursor;
/**
 * Interface for all classes that extract a data object from a database cursor.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of data to extract
 */
public interface CursorDataExtractor<T> {

  public T extract(Cursor cursor, Query query);
}
