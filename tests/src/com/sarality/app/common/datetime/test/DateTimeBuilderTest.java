package com.sarality.app.common.datetime.test;

import hirondelle.date4j.DateTime;

import java.util.Date;
import java.util.TimeZone;

import junit.framework.TestCase;

import com.sarality.app.common.datetime.DateTimeBuilder;

/**
 * Tests for {@link DateTimeBuilder}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DateTimeBuilderTest extends TestCase {

  public void testConstructor() {
    Date today = new Date();
    DateTime dateToday = new DateTime(today.toString());
    DateTimeBuilder dateTimeBuilder = new DateTimeBuilder(dateToday);
    assertNotNull(dateTimeBuilder);
  }

  public void testToday() {
    DateTime dateToday = DateTime.today(TimeZone.getDefault());
    assertEquals(dateToday, DateTimeBuilder.today().build());
  }

  public void testNow() {
    DateTime now = DateTime.now(TimeZone.getDefault());
    assertSame(now.getMinute(), DateTimeBuilder.now().build().getMinute());
  }

  public void testTime() {
    DateTimeBuilder dateTimeBuilder = DateTimeBuilder.time(12, 30, 59);
    assertSame(12, dateTimeBuilder.build().getHour());
    assertSame(30, dateTimeBuilder.build().getMinute());
    assertSame(59, dateTimeBuilder.build().getSecond());
  }

  public void testDate() {
    DateTimeBuilder dateTimeBuilder = DateTimeBuilder.date(2014, 7, 22);
    assertSame(22, dateTimeBuilder.build().getDay());
    assertSame(7, dateTimeBuilder.build().getMonth());
    assertEquals(Integer.valueOf(2014), Integer.valueOf(dateTimeBuilder.build().getYear()));
    assertSame(DateTime.today(TimeZone.getDefault()).getHour(), dateTimeBuilder.build().getHour());
  }

  public void testPlusDays() {
    DateTime date = DateTime.today(TimeZone.getDefault());
    DateTimeBuilder dateTimeBuilder = DateTimeBuilder.today().plusDays(31);
    assertNotSame(date.getMonth(), dateTimeBuilder.build().getMonth());
  }

  public void testPlusHours() {
    DateTime date = DateTime.today(TimeZone.getDefault()).plus(0, 0, 0, 12, 0, 0, 0, DateTime.DayOverflow.Spillover);
    DateTimeBuilder dateTimeBuilder = DateTimeBuilder.today().plusHours(12);
    assertEquals(date, dateTimeBuilder.build());
  }

  public void testPlusMinutes() {
    DateTime date = DateTime.today(TimeZone.getDefault()).plus(0, 0, 0, 0, 60, 0, 0, DateTime.DayOverflow.Spillover);
    DateTimeBuilder dateTimeBuilder = DateTimeBuilder.today().plusMinutes(60);
    assertEquals(date, dateTimeBuilder.build());
  }
}
