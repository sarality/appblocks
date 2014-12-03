package com.sarality.app.data.location;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.data.EnumData;
import com.sarality.app.data.image.AppImage;

/**
 * Enumeration of all Cities predefined in the system.
 * <p/>
 * Class is present only a convenience when strongly types usage is required. The Actual lookup of the data should
 * happen using {@link City}.
 */
public enum CityEnum implements EnumData<City> {
  DELHI("DELHI_IN", "Delhi", "New Delhi"),
  MUMBAI("MUMBAI_IN", "Mumbai", "Mumbai");

  private final String enumName;
  private final String shortName;
  private final String fullName;

  private CityEnum(String enumName, String shortName, String fullName) {
    this.enumName = enumName;
    this.shortName = shortName;
    this.fullName = fullName;
  }

  @Override
  public City getEnumData() {
    return new City(enumName, shortName, fullName, Lists.<AppImage>of());
  }

  @Override
  public String getEnumName() {
    return name();
  }
}
