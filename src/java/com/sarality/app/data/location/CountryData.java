package com.sarality.app.data.location;

import com.sarality.app.data.DataObject;
import com.sarality.app.data.image.AppImage;

import java.util.List;

/**
 * Data object for a Country stored in a Persistent store.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CountryData implements DataObject<CountryData> {
  private final Long countryId;
  private final Country country;

  public CountryData(Long countryId, Country country) {
    this.countryId = countryId;
    this.country = country;
  }

  @Override
  public
  CountryDataBuilder getBuilder() {
    return new CountryDataBuilder().setCountryId(countryId).setCountry(country);
  }

  @Override
  public CountryDataBuilder newBuilder() {
    return new CountryDataBuilder();
  }
}
