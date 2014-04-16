package com.sarality.app.datastore.extractor;

import com.sarality.app.data.DataObject;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.Query;

import android.database.Cursor;
/**
 * Base implementation for the {@link CursorDataExtractor}
 * <p>
 * Contains some utility methods as well as instances of the extractors of various types
 * for easy access in the concrete classes.
 *
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of {@link DataObject} to be extracted from the Cursor.
 */
public abstract class BaseCursorDataExtractor<T extends DataObject<T>> implements CursorDataExtractor<T> {

  protected final LongValueExtractor longExtractor = new LongValueExtractor();
  protected final StringValueExtractor stringExtractor = new StringValueExtractor();
  protected final DateValueExtractor dateExtractor = new DateValueExtractor();
  protected final BooleanValueExtractor booleanExtractor = new BooleanValueExtractor();

  /**
   * Indicates whether the given Column is returned by the cursor.
   * 
   * @param cursor The cursor that we are extracting values from.
   * @param query The query that generated the cursor.
   * @param column The that we are checking for
   * @return {@code true} if the column exists in the cursor, {@code false} otherwise.
   */
  public final boolean hasColumn(Cursor cursor, Query query, Column column) {
    return cursor.getColumnIndex(column.getName()) > -1;
  }  
}
