package com.sarality.app.datastore.extractor;

import android.database.Cursor;

import com.sarality.app.datastore.Column;

/**
 * Extract an enum value from the cursor column.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of Enum to be extracted.
 */
public class EnumValueExtractor<T extends Enum<T>> implements ColumnValueExtractor<T> {

  private Class<T> enumType;
  
  public EnumValueExtractor(Class<T> enumType) {
    this.enumType = enumType;
  }
  
  @Override
  public T extract(Cursor cursor, Column column) {
    String stringValue = cursor.getString(cursor.getColumnIndex(column.getName()));
    return Enum.valueOf(enumType, stringValue);
  }
}
