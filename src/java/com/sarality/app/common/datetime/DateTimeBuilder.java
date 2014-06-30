package com.sarality.app.common.datetime;

import java.util.TimeZone;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;

/**
 * Utility to build and manipulate a DateTime object.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class DateTimeBuilder {
  /**
   * DateTime object created by the builder so far
   */
  private DateTime dateTime;
  
  /**
   * Create a builder with the given DateTime as the initial date and time.
   * 
   * @param dateTime The DateTime to initialize the builder with.
   */
  public DateTimeBuilder(DateTime dateTime) {
    this.dateTime = dateTime;
  }

  /**
   * @return A DateTimeBuilder with the date set as today using the default Timezone
   */
  public static DateTimeBuilder today() {
    return new DateTimeBuilder(DateTime.today(TimeZone.getDefault()));
  }

  /**
   * @return A DateTimeBuilder with the time set as now using the default Timezone
   */
  public static DateTimeBuilder now() {
    return new DateTimeBuilder(DateTime.now(TimeZone.getDefault()));
  }

  /**
   * Returns a DateTimeBuilder with the time set as given house minute and sec
   * 
   * @param hour Hour value of the time
   * @param minute Minute value of the time
   * @param sec  Second value of the time
   * @return A DateTimeBuilder with the date set as today using the default Timezone
   */
  public static DateTimeBuilder time(int hour, int minute, int sec) {
    return new DateTimeBuilder(DateTime.forTimeOnly(hour, minute, sec, 0));
  }

  /**
   * Returns DateTimeBuilder with the date set as the given year, month and day.
   * 
   * @param year Year value of the date.
   * @param month Month value of the date.
   * @param day Day value of the date.
   * @return A DateTimeBuilder with the date set as the given year, month and day.
   */
  public static DateTimeBuilder date(int year, int month, int day) {
    return new DateTimeBuilder(DateTime.forDateOnly(year, month, day));
  }
  
  /**
   * Returns a DateTimeBuilder with the given number of days added to it.
   * 
   * @param days Number of days to add to the DateTime.
   * @return DateTimeBuilder with the given number of days added to it.
   */
  public DateTimeBuilder plusDays(int days) {
    dateTime = dateTime.plusDays(days);
    return this;
  }
  
  /**
   * Returns a DateTimeBuilder with the given number of hours added to it.
   * 
   * @param hours Number of hours to add to the DateTime.
   * @return DateTimeBuilder with the given number of days added to it.
   */
  public DateTimeBuilder plusHours(int hours) {
    dateTime = dateTime.plus(0, 0, 0, hours, 0, 0, 0, DayOverflow.Spillover);
    return this;
  }

  /**
   * Returns a DateTimeBuilder with the given number of minutes added to it.
   * 
   * @param minutes NUmber of minutes to add to the DateTime.
   * @return DateTimeBuilder with the given number of days added to it.
   */
  public DateTimeBuilder plusMinutes(int minutes) {
    dateTime = dateTime.plus(0, 0, 0, 0, minutes, 0, 0, DayOverflow.Spillover);
    return this;
  }

  /**
   * @return The generated DateTime object.
   */
  public DateTime build() {
    return dateTime;
  }
}
