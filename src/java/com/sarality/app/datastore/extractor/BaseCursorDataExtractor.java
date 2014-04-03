package com.sarality.app.datastore.extractor;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.Query;

import android.database.Cursor;

public abstract class BaseCursorDataExtractor<T> implements CursorDataExtractor<T> {
  protected final LongValueExtractor longExtractor = new LongValueExtractor();

  public final boolean hasColumn(Cursor cursor, Query query, Column column) {
    return true;
  }
}
