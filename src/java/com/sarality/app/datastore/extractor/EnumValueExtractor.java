package com.sarality.app.datastore.extractor;

import android.database.Cursor;

import com.sarality.app.datastore.Column;

public class EnumValueExtractor<T extends Enum<T>> implements ColumnValueExtractor<T> {

  @Override
  public T extract(Cursor cursor, Column column, Class<T> returnType) {
    String stringValue = cursor.getString(cursor.getColumnIndex(column.getName()));
    return Enum.valueOf(returnType, stringValue);
  }

  @Override
  public T extract(Cursor cursor, Column column) {
    throw new UnsupportedOperationException("EnumValueExtractor must be called with the class"
        + " of the Enum specified");
  }
}
