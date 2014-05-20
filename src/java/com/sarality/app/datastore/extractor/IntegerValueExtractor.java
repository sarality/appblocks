package com.sarality.app.datastore.extractor;

import android.database.Cursor;

import com.sarality.app.datastore.Column;

public class IntegerValueExtractor implements ColumnValueExtractor<Integer> {
  @Override
  public Integer extract(Cursor cursor, Column column) {
    return cursor.getInt(cursor.getColumnIndex(column.getName()));
  }
}
