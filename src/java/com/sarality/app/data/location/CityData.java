package com.sarality.app.data.location;

import com.sarality.app.data.DataObject;
import com.sarality.app.data.DataTransformer;
import com.sarality.app.data.image.AppImage;

import java.util.List;

/**
 * Data object for a City stored in a Persistent store.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CityData implements DataObject<CityData> {
  private final Long cityId;
  private final City city;
  private final Country country;

  private final List<AppImage> imageList;

  public CityData(Long cityId, City city, Country country, List<AppImage> imageList) {
    this.cityId = cityId;
    this.city = city;
    this.country = country;
    this.imageList = imageList;
  }

  @Override
  public CityDataBuilder getBuilder() {
    return new CityDataBuilder().setCityId(cityId).setCity(city).setCountry(country).setImageList(imageList);
  }

  @Override
  public CityDataBuilder newBuilder() {
    return new CityDataBuilder();
  }

  public static class CityDataGenerator implements DataTransformer<City, CityData> {

    @Override public CityData transform(City city) {
      // TODO(abhideep): Add support for Country
      return new CityData(null, city, null, null);
    }
  }
}
