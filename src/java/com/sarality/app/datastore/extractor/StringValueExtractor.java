package com.sarality.app.datastore.extractor;

import android.database.Cursor;

import com.sarality.app.datastore.Column;

/**
 * Extract String value from a Column.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class StringValueExtractor implements ColumnValueExtractor<String> {

  @Override
  public String extract(Cursor cursor, Column column) {
    return cursor.getString(cursor.getColumnIndex(column.getName()));
  }

  @Override
  public String extract(Cursor cursor, Column column, Class<String> returnType) {
    return extract(cursor, column);
  }
}
