package com.sarality.app.db.extractor;

import android.database.Cursor;

import com.sarality.app.data.DataObject;
import com.sarality.app.db.Column;

public abstract class ColumnProcessor<B extends DataObject.Builder<?>, V> {

  public final void extractAndSetValue(Cursor cursor, Column column, B builder) {
    V value = extract(cursor, column);
    if (value != null) {
      setValue(builder, value);
    }
  }

  public abstract V extract(Cursor cursor, Column column);
  
  public abstract void setValue(B builder, V value);

}
