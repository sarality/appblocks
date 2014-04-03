package com.sarality.app.datastore.extractor;

import android.database.Cursor;

import com.sarality.app.datastore.Column;

/**
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <V>
 */
public interface ColumnValueExtractor<V> {

  public V extract(Cursor cursor, Column column);
  
  public V extract(Cursor cursor, Column column, Class<V> returnType);
}
