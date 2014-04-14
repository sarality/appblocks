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

  /**
   * Extract the value from the cursor for the given column and the Class for the data type to be returned.
   * <p>
   * This method is mostly there for Enum value extraction, which requires the class is Enum.valueOf() call.
   * For the rest, both methods return the same value.
   * 
   * @param cursor The cursor to extract the value from
   * @param column The column that needs to be extracted
   * @param returnType The class of the object to be extracted.
   * @return Extracted data of the given type.
   */
  public V extract(Cursor cursor, Column column, Class<V> returnType);
}
