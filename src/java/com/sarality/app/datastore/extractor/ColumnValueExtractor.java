package com.sarality.app.datastore.extractor;

import android.database.Cursor;

import com.sarality.app.datastore.Column;

/**
 * Interface for all classes that extract a value from Cursor for the given Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <V> The type of value to be extracted
 */
public interface ColumnValueExtractor<V> {

  /**
   * Extract the value from the cursor for the given column.
   * 
   * @param cursor The cursor to extract the value from
   * @param column The column that needs to be extracted
   * @return Extracted data of the given type.
   */
  public V extract(Cursor cursor, Column column);
}
