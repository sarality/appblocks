package com.sarality.app.common.datetime;

import java.util.Locale;

import hirondelle.date4j.DateTime;

/**
 * Formats DateTime into one of the time formats
 *
 * @author sunayna (Sunayna Uberoy)
 */
public class DateTimeFormatter {
  public enum Format {
    TIME_ONLY("hh12:mm a"),
    DATE_ONLY("WWW, MMM DD");

    private final String format;

    Format(String format) {
      this.format = format;
    }

    public String getFormat() {
      return format;
    }
  }

  public static String getStringValue(DateTime dateValue, Format format) {
    return dateValue.format(format.getFormat(), Locale.getDefault());
  }
}
