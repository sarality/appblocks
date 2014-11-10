package com.sarality.app.common.datetime;

/**
 * Class to store hour, minutes and seconds
 * 
 * @author sunayna(Sunayna Uberoy)
 */
public class TimeDuration {
  private final int hours;
  private final int minutes;
  private final int seconds;

  public TimeDuration(int hours, int minutes, int seconds) {
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public final int getHours() {
    return hours;
  }

  public final int getMinutes() {
    return minutes;
  }

  public final int getSeconds() {
    return seconds;
  }
}
