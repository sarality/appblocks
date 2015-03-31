package com.sarality.app.data.location;

import com.sarality.app.data.BaseEnumData;

import java.util.List;

/**
 * Enum Data for a City.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class City extends BaseEnumData<City> {

  private final String name;
  private final Country country;
  private final List<String> alternateNames;

  public City(String enumName, String name, Country country, List<String> alternateNames) {
    super(enumName);
    this.name = name;
    this.country = country;
    this.alternateNames = alternateNames;
    register(City.class, this);
  }

  public String getShortName() {
    return name;
  }

  public String getFullName() {
    return name;
  }

  public Country getCountry() {
    return country;
  }

  public List<String> getAlternateNames() {
    return alternateNames;
  }

  @Override
  public City getEnumData() {
    return this;
  }
}
