package com.sarality.app.datastore.extractor;

import android.database.Cursor;
import android.util.Log;

import com.sarality.app.datastore.Column;

public class BooleanValueExtractor implements ColumnValueExtractor<Boolean> {

  private static final String TAG = "BooleanValueExtractor";

  @Override
  public Boolean extract(Cursor cursor, Column column) {
    int intValue = cursor.getInt(cursor.getColumnIndex(column.getName()));
    if (intValue != 0 && intValue != 1) {
      Log.e(TAG, "Boolean Column " + column.getName() + " has invalid value " + intValue + ". Returning FALSE");
    }
    return (intValue == 1);
  }
}
