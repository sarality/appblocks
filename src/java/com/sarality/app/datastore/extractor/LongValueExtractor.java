package com.sarality.app.datastore.extractor;

import com.sarality.app.datastore.Column;

import android.database.Cursor;

public class LongValueExtractor implements ColumnValueExtractor<Long> {

  @Override
  public Long extract(Cursor cursor, Column column) {
    return cursor.getLong(cursor.getColumnIndex(column.getName()));
  }

  @Override
  public Long extract(Cursor cursor, Column column, Class<Long> returnType) {
    return extract(cursor, column);
  }
}
