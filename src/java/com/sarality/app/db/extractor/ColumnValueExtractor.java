package com.sarality.app.db.extractor;

import android.database.Cursor;

import com.sarality.app.db.Column;

public interface ColumnValueExtractor<V> {

  public V extract(Cursor cursor, Column column);
}
