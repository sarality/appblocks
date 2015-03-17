package com.sarality.app.datastore.column;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import android.content.ContentValues;
import android.database.Cursor;

import com.sarality.app.common.collect.Maps;
import com.sarality.app.data.field.Field;
import com.sarality.app.data.field.Field.DataType;
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
public class DateTimeColumnProcessor implements ColumnProcessor<DateTime> {

  private static final String DEFAULT_DATETIME_FORMAT = "YYYY-MM-DD hh:mm:ss";
  private static final String DEFAULT_DATEONLY_FORMAT = "YYYY-MM-DD";
  private static final String DEFAULT_TIMEONLY_FORMAT = "hh:mm:ss";
  private static final String DATE_AS_INT_FORMAT = "YYYYMMDDhhmmss";

  private static final Map<Field.DataType, String> FORMAT_MAP = Maps.<Field.DataType, String> builder()
      .with(Field.DataType.DATETIME, DEFAULT_DATETIME_FORMAT).with(Field.DataType.DATE_ONLY, DEFAULT_DATEONLY_FORMAT)
      .with(Field.DataType.TIME_ONLY, DEFAULT_TIMEONLY_FORMAT).build();

  private static final SimpleDateFormat DATE_AS_INT_DATETIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss",
      Locale.getDefault());
  private static final SimpleDateFormat DATE_AS_INT_DATEONLY_FORMAT = new SimpleDateFormat("yyyyMMdd",
      Locale.getDefault());
  private static final SimpleDateFormat DATE_AS_INT_TIMEONLY_FORMAT = new SimpleDateFormat("HHmmss",
      Locale.getDefault());

  private static final Map<Field.DataType, SimpleDateFormat> DATE_AS_INT_FORMAT_MAP = Maps
      .<Field.DataType, SimpleDateFormat> builder().with(Field.DataType.DATETIME, DATE_AS_INT_DATETIME_FORMAT)
      .with(Field.DataType.DATE_ONLY, DATE_AS_INT_DATEONLY_FORMAT)
      .with(Field.DataType.TIME_ONLY, DATE_AS_INT_TIMEONLY_FORMAT).build();

  private final Field.DataType dataType;

  public DateTimeColumnProcessor(Field.DataType dataType) {
    this.dataType = dataType;
  }

  private final String getDefaultFormat() {
    return FORMAT_MAP.get(dataType);
  }

  private final SimpleDateFormat getDateAsIntegerFormat() {
    return DATE_AS_INT_FORMAT_MAP.get(dataType);
  }

  @Override
  public DateTime extract(Cursor cursor, Column column) {
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
        DateTime dateTime = DateTime.forInstant(dateValue, TimeZone.getDefault());
        return getDateTimePart(dateTime);
      } catch (NumberFormatException e) {
        String value = cursor.getString(columnIndex);
        throw new IllegalStateException("Cannot extract DateTime value from Column " + column.getName()
            + ". Cannot convert value stored in Epoch Date column to DateTime : " + value);
      }
    } else if (format == ColumnFormat.DATE_AS_INT) {
      String value = cursor.getString(columnIndex);
      try {
        SimpleDateFormat dateFormat = getDateAsIntegerFormat();
        DateTime dateTime = DateTime.forInstant(dateFormat.parse(value).getTime(), TimeZone.getDefault());
        return getDateTimePart(dateTime);
      } catch (ParseException e) {
        throw new IllegalStateException("Cannot extract DateTime value from Column " + column.getName()
            + ". Cannot convert value stored in Date as Integer column to DateTime : " + value);
      }
    } else {
      String value = cursor.getString(columnIndex);
      if (DateTime.isParseable(value)) {
        DateTime dateTime = new DateTime(value);
        return getDateTimePart(dateTime);
      } else {
        throw new IllegalStateException("Cannot extract DateTime value from Column " + column.getName()
            + ". Cannot convert value stored in column to a DateTime : " + value);
      }
    }
  }

  @Override
  public void populate(ContentValues contentValues, Column column, DateTime value) {
    if (value == null) {
      contentValues.putNull(column.getName());
    } else {
      ColumnFormat format = column.getSpec().getFormat();
      if (format == ColumnFormat.EPOCH) {
        DateTime dateTimeValue = getDateTimePart(value);
        // Have to do something special to avoid NPE with TimeOnly values.
        if (dataType == DataType.TIME_ONLY) {
          dateTimeValue = DateTime.forInstant(0, TimeZone.getDefault());
          dateTimeValue = dateTimeValue.plus(0, 0, 0, value.getHour(), value.getMinute(), value.getSecond(), 0,
              DayOverflow.Spillover);
        }
        contentValues.put(column.getName(), dateTimeValue.getMilliseconds(TimeZone.getDefault()));
      } else if (format == ColumnFormat.DATE_AS_INT) {
        DateTime dateTimeValue = getDateTimePart(value);
        String dateString = dateTimeValue.format(DATE_AS_INT_FORMAT);
        contentValues.put(column.getName(), Long.valueOf(dateString));
      } else {
        String dateFormat = getDefaultFormat();
        contentValues.put(column.getName(), value.format(dateFormat, Locale.getDefault()));
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
    if (dataType == Field.DataType.DATETIME || dataType == Field.DataType.DATE_ONLY
        || dataType == Field.DataType.TIME_ONLY) {
      populate(contentValues, column, (DateTime) fieldValue.getValue());
    } else {
      throw new IllegalArgumentException("Cannot convert data for type " + dataType
          + " to a DateTime while adding value for column " + column.getName());
    }
  }

  private DateTime getDateTimePart(DateTime dateTime) {
    if (dataType == DataType.DATE_ONLY) {
      return DateTime.forDateOnly(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
    } else if (dataType == DataType.TIME_ONLY) {
      return DateTime.forTimeOnly(dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond(), 0);
    } else {
      return new DateTime(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), dateTime.getHour(),
          dateTime.getMinute(), dateTime.getSecond(), 0);
    }
  }
}
