package com.sarality.app.datastore.populator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;

import com.sarality.app.data.FieldData;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnFormat;

/**
 * Populates a ContentValues instance with data for a Date Column.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DateValuePopulator implements ValuePopulator<Date>, FieldDataValuePopulator {
  SimpleDateFormat defaultFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
  SimpleDateFormat dateAsIntegerFormatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);

  @Override
  public void populate(ContentValues contentValues, Column column, Date value,
      boolean valueExistsPreCondition) {
    if (!valueExistsPreCondition) {
      return;
    }
    ColumnFormat format = column.getConfig().getSpec().getFormat();
    if (format == null) {
      contentValues.put(column.getName(), defaultFormatter.format(value));
    } else if (format == ColumnFormat.EPOCH) {
      contentValues.put(column.getName(), value.getTime());
    } else if (format == ColumnFormat.DATE_AS_INT) {
      String dateString = dateAsIntegerFormatter.format(value);
      contentValues.put(column.getName(), Integer.valueOf(dateString));
    }
  }

  @Override
  public void populate(ContentValues values, Column column, FieldData<?> data) {
    FieldData.Type dataType = data.getType();
    if (dataType == FieldData.Type.DATE) {
      populate(values, column, (Date) data.getValue(), data.hasValue());
    } else {
      throw new IllegalArgumentException(" Cannot convert data for type " + dataType 
          + " to DATE while adding value for column " + column.getName());
    }
  }
}
