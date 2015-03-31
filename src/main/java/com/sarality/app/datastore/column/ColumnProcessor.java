package com.sarality.app.datastore.column;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;

/**
 * Interface for classes that process the extraction and population of the Data Store Columns.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <V> Type of Data that the Column stores. This is not the underlying data type, but the class that is 
 * exposed to the application by the the Data Store.
 */
public interface ColumnProcessor<V> {

  /**
   * Extract the value from the cursor for the given column.
   * 
   * @param cursor The cursor to extract the value from.
   * @param column The column that needs to be extracted.
   * @return Extracted data of the given type.
   */
  public V extract(Cursor cursor, Column column);

  /**
   * Populate the ContentValues for the column with the given value.
   * 
   * @param values ContentValues to be populated
   * @param column Column for which data needs to be added to the ContentValues.
   * @param value The value that needs to added to the content values.
   */
  public void populate(ContentValues contentValues, Column column, V value);  
  
  /**
   * Populate the ContentValues for the Column with the given field value.
   * 
   * @param values ContentValues to be populated
   * @param column Column for which data needs to be added to the ContentValues.
   * @param value The value that needs to added to the content values.
   */
  public void populate(ContentValues contentValues, Column column, FieldValue<?> fieldValue);  
}
