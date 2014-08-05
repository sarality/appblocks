package com.sarality.app.datastore.column;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.FieldValue;
import com.sarality.app.datastore.Column;
import com.sarality.app.datastore.ColumnFormat;

/**
 * Populates a ContentValues instance with data for a Date Column.
 * 
 * TODO(abhideep): Add support for TimeZones
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DateColumnProcessor implements ColumnProcessor<Date> {

  private static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
      Locale.getDefault());
 
  private static final SimpleDateFormat DATE_AS_INT_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss", 
      Locale.getDefault());
  
  @Override
  public Date extract(Cursor cursor, Column column) {
    int columnIndex = cursor.getColumnIndex(column.getName());
    if (columnIndex < 0) {
      throw new IllegalArgumentException("Column with name " + column.getName() + " not found.");
    }
    if (cursor.isNull(columnIndex)) {
      return null;
    }

    ColumnFormat format = column.getSpec().getFormat();
    if (format == ColumnFormat.EPOCH) {
      try {
        long dateValue = cursor.getLong(columnIndex);
        return new Date(dateValue);
      } catch (NumberFormatException e) {
        String value = cursor.getString(columnIndex);
        throw new IllegalStateException("Cannot extract Date value from Column " + column.getName()
            + ". Cannot convert value stored in Epoch Date column to Date : " + value);
      }
    } else if (format == ColumnFormat.DATE_AS_INT) {
      String value = cursor.getString(columnIndex);
      try {
        return DATE_AS_INT_FORMAT.parse(value);
      } catch (ParseException e) {
        throw new IllegalStateException("Cannot extract Date value from Column " + column.getName()
            + ". Cannot convert value stored in Integer column to Date : " + value);
      }
    } else {
      String value = cursor.getString(columnIndex);
      try {
        return DEFAULT_FORMAT.parse(value);
      } catch (ParseException e) {
        throw new IllegalStateException("Cannot extract Date value from Column " + column.getName()
            + ". Cannot convert value stored in column to a Date : " + value);
      }
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, Date value) {
    if (value == null) {
      contentValues.putNull(column.getName());
    } else {
      ColumnFormat format = column.getSpec().getFormat();
      if (format == ColumnFormat.EPOCH) {
        contentValues.put(column.getName(), value.getTime());
      } else if (format == ColumnFormat.DATE_AS_INT) {
        String dateString = DATE_AS_INT_FORMAT.format(value);
        contentValues.put(column.getName(), Long.valueOf(dateString));
      } else {
        String dateString = DEFAULT_FORMAT.format(value);
        contentValues.put(column.getName(), dateString);
      }
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, FieldValue<?> fieldValue) {
    if (fieldValue == null || !fieldValue.hasValue() || fieldValue.getValue() == null) {
      contentValues.putNull(column.getName());
      return;
    }

    Field.DataType dataType = fieldValue.getDataType();
    if (dataType == Field.DataType.DATE) {
      populate(contentValues, column, (Date) fieldValue.getValue());
    } else {
      throw new IllegalArgumentException("Cannot convert data for type " + dataType
          + " to a Date while adding value for column " + column.getName());
    }
  }
}
