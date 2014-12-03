package com.sarality.app.data.location;

import com.sarality.app.data.EnumData;

/**
 * Enumeration of all Countries predefined in the system.
 * <p>
 * Class is present only a convenience when strongly types usage is required. The Actual lookup of the data should
 * happen using {@link Country}.
 */
public enum CountryEnum implements EnumData<Country> {
  AUSTRALIA("AU", "Australia"),
  FRANCE("FR", "France"),
  GERMANY("DE", "Germany"),
  INDIA("IN", "India"),
  ITALY("IT", "Italy"),
  SPAIN("ES", "Spain"),
  UK("UK", "UK"),
  USA("US", "USA");

  private final String countryCode;
  private final String fullName;

  private  CountryEnum(String countryCode, String fullName) {
    this.countryCode = countryCode;
    this.fullName = fullName;
  }

  @Override
  public Country getEnumData() {
    return new Country(countryCode, fullName);
  }

  @Override
  public String getEnumName() {
    return name();
  }
}
