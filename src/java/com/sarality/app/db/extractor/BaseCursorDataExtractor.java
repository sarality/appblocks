package com.sarality.app.db.extractor;

import com.sarality.app.db.Column;
import com.sarality.app.db.Query;

import android.database.Cursor;

public abstract class BaseCursorDataExtractor<T> implements CursorDataExtractor<T> {
  protected final LongValueExtractor longExtractor = new LongValueExtractor();

  public final boolean hasColumn(Cursor cursor, Query query, Column column) {
    return true;
  }
}
