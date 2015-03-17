package com.sarality.app.data.location;

import com.sarality.app.data.DataObject;
import com.sarality.app.data.image.AppImage;

import java.util.List;

/**
 * Builder for {@link CountryData}
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CountryDataBuilder implements DataObject.Builder<CountryData> {

  private Long countryId;
  private Country country;

  @Override
  public CountryData build() {
    return new CountryData(countryId, country);
  }

  public CountryDataBuilder setCountryId(Long countryId) {
    this.countryId = countryId;
    return this;
  }

  public CountryDataBuilder setCountry(Country country) {
    this.country = country;
    return this;
  }
}
