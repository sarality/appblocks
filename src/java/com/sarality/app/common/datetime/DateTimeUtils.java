package com.sarality.app.common.datetime;

import hirondelle.date4j.DateTime;

/**
 * Utility class to perform simple DateTime conversions and comparisons.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DateTimeUtils {

  public static DateTime toDateOnly(DateTime dateTime) {
    return DateTime.forDateOnly(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay());
  }

  public static DateTime toTimeOnly(DateTime dateTime) {
    return DateTime.forTimeOnly(dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond(),
        dateTime.getNanoseconds());
  }

  public static boolean isSameTime(DateTime lhs, DateTime rhs) {
    if (lhs == null && rhs == null) {
      return true;
    }
    if (lhs == null || rhs == null) {
      return false;
    }
    return lhs.getHour().equals(rhs.getHour()) && lhs.getMinute().equals(rhs.getMinute())
        && lhs.getSecond().equals(rhs.getSecond());
  }
}
