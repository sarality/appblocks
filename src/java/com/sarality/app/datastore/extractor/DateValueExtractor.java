package com.sarality.app.datastore.extractor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.database.Cursor;
import android.util.Log;

import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnFormat;

/**
 * Extract a date value from the cursor column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DateValueExtractor implements ColumnValueExtractor<Date> {
  
  private static final String TAG = "DateValueExtractor";
  
  private final SimpleDateFormat defaultFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
      Locale.US);
  SimpleDateFormat dateAsIntegerFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);

  @Override
  public Date extract(Cursor cursor, Column column) {
    ColumnFormat format = column.getSpec().getFormat();
    if (format == null) {
      String dateStr = cursor.getString(cursor.getColumnIndex(column.getName()));
      if (dateStr != null) {
        try {
          return defaultFormatter.parse(dateStr);
        } catch (ParseException e) {
          Log.e(TAG, "Could not parse date value " + dateStr + " stored in column " + column.getName());
        }
      }
    } else if (format == ColumnFormat.EPOCH) {
      long dateValue = cursor.getLong(cursor.getColumnIndex(column.getName()));
      return new Date(dateValue);
    } else if (format == ColumnFormat.DATE_AS_INT) {
      String dateStr = cursor.getString(cursor.getColumnIndex(column.getName()));
      if (dateStr != null) {
        try {
          return dateAsIntegerFormatter.parse(dateStr);
        } catch (ParseException e) {
          Log.e(TAG, "Could not parse date value " + dateStr + " stored in column " + column.getName());
        }
      }
    }
    // Return null by default
    return null;
  }
}
