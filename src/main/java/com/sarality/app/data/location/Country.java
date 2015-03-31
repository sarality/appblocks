package com.sarality.app.data.location;

import com.sarality.app.data.BaseEnumData;

/**
 * Data for a Country.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class Country extends BaseEnumData<Country> {

  private final String fullName;

  public Country(String countryCode, String fullName) {
    super(countryCode);
    this.fullName = fullName;
    register(Country.class, this);
  }

  public final String getCountryCode() {
    return super.getEnumName();
  }

  public final String getFullName() {
    return fullName;
  }

  @Override
  public final Country getEnumData() {
    return this;
  }
}
