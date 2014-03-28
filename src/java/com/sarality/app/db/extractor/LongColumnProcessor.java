package com.sarality.app.db.extractor;

import android.database.Cursor;

import com.sarality.app.data.DataObject;
import com.sarality.app.db.Column;

public abstract class LongColumnProcessor<B extends DataObject.Builder<?>> 
    extends ColumnProcessor<B, Long> {

  @Override
  public Long extract(Cursor cursor, Column column) {
    // TODO Auto-generated method stub
    return null;
  }

}
