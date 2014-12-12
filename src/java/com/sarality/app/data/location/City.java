package com.sarality.app.data.location;

import com.sarality.app.data.BaseEnumData;

/**
 * Enum Data for a City.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class City extends BaseEnumData<City> {

  private final String shortName;
  private final String fullName;
  private final Country country;

  public City(String enumName, String shortName, String fullName, Country country) {
    super(enumName);
    this.shortName = shortName;
    this.fullName = fullName;
    this.country = country;
    register(City.class, this);
  }

  public String getShortName() {
    return shortName;
  }

  public String getFullName() {
    return fullName;
  }

  public Country getCountry() {
    return country;
  }

  @Override
  public City getEnumData() {
    return this;
  }
}
