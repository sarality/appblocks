package com.sarality.app.datastore.extractor;

import android.database.Cursor;

import com.sarality.app.datastore.Column;

public class DoubleValueExtractor implements ColumnValueExtractor<Double> {

  @Override
  public Double extract(Cursor cursor, Column column) {
    return cursor.getDouble(cursor.getColumnIndex(column.getName()));
  }
}
