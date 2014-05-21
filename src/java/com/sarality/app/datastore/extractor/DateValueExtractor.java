package com.sarality.app.datastore.extractor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
      Locale.getDefault());

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
        // Return null by default
        return null;
      }
    } else if (format == ColumnFormat.EPOCH) {
      long dateValue = cursor.getLong(cursor.getColumnIndex(column.getName()));
      return new Date(dateValue);
    } else if (format == ColumnFormat.DATE_AS_INT) {
      long dateValue = cursor.getLong(cursor.getColumnIndex(column.getName()));
      int year = Long.valueOf(dateValue / 10000000000L).intValue();

      dateValue = dateValue - (year * 10000000000L);
      int month = Long.valueOf(dateValue / 100000000L).intValue();

      dateValue = dateValue - (month * 100000000L);
      int date = Long.valueOf(dateValue / 1000000L).intValue();

      dateValue = dateValue - (date * 1000000L);
      int hour = Long.valueOf(dateValue / 10000L).intValue();

      dateValue = dateValue - (hour * 10000L);
      int min = Long.valueOf(dateValue / 10000L).intValue();

      dateValue = dateValue - (year * 10000L);
      int sec = Long.valueOf(dateValue).intValue();

      Calendar cal = Calendar.getInstance();
      cal.set(year, month - 1, date, hour, min, sec);
      
      return cal.getTime();
    }
    return null;
  }
}
