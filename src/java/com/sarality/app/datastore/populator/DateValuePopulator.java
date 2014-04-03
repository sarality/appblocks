package com.sarality.app.datastore.populator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;

import com.sarality.app.datastore.Column;

/**
 * Populates a ContentValues instance with data for a Date Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DateValuePopulator implements ValuePopulator<Date> {
  SimpleDateFormat defaultFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
  SimpleDateFormat dateAsIntegerFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);

  @Override
  public void populate(ContentValues contentValues, Column column, Date value,
      boolean valueExistsPreCondition) {
    if (!valueExistsPreCondition) {
      return;
    }
    Column.DataTypeFormat format = column.getFormat();
    if (format == null) {
      contentValues.put(column.getName(), defaultFormatter.format(value));
    } else if (format == Column.DataTypeFormat.EPOCH) {
      contentValues.put(column.getName(), value.getTime());
    } else if (format == Column.DataTypeFormat.DATE_AS_INT) {
      String dateString = dateAsIntegerFormatter.format(value);
      contentValues.put(column.getName(), Integer.valueOf(dateString));
    }
  }
}
