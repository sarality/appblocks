package com.sarality.app.data.location;

import com.sarality.app.data.DataObject;
import com.sarality.app.data.image.AppImage;

import java.util.List;

/**
 * Builder for {@link CityData}
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CityDataBuilder implements DataObject.Builder<CityData> {

  private Long cityId;
  private City city;
  private Country country;

  private List<AppImage> imageList;

  @Override
  public CityData build() {
    return new CityData(cityId, city, country, imageList);
  }

  public CityDataBuilder setCityId(Long cityId) {
    this.cityId = cityId;
    return this;
  }

  public CityDataBuilder setCity(City city) {
    this.city = city;
    return this;
  }

  public CityDataBuilder setCountry(Country country) {
    this.country = country;
    return this;
  }

  public CityDataBuilder setImageList(List<AppImage> imageList) {
    this.imageList = imageList;
    return this;
  }
}
