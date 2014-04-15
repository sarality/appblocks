package com.sarality.app.datastore.extractor;

import com.sarality.app.datastore.Column;

import android.database.Cursor;

/**
 * Extract a long value from the cursor column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class LongValueExtractor implements ColumnValueExtractor<Long> {

  @Override
  public Long extract(Cursor cursor, Column column) {
    return cursor.getLong(cursor.getColumnIndex(column.getName()));
  }
}
