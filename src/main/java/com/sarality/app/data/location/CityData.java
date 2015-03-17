package com.sarality.app.data.location;

import com.sarality.app.data.EnumDataObject;
import com.sarality.app.data.image.AppImage;
import com.sarality.app.datastore.EnumDataObjectComposer;
import com.sarality.app.datastore.EnumDataObjectLoader;

import java.util.List;

/**
 * Data object for a City stored in a Persistent store.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class CityData implements EnumDataObject<City, CityData> {
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

  @Override public City getEnumData() {
    return city;
  }

  public City getCity() {
    return city;
  }

  public Country getCountry() {
    return country;
  }

  public List<AppImage> getImageList() {
    return imageList;
  }

  /**
   * Enum for components of CityData
   * <p/>
   * TODO(abhideep): Look into potentially using Fields here in the Future.
   */
  public enum Component {
    IMAGE,
  }

  /**
   * Composes an AirlineData object for a given Airline and Map of Associated Data Providers.
   *
   * @author abhideep@ (Abhideep Singh)
   */
  public static class Composer implements EnumDataObjectComposer<City, CityData> {

    @Override
    public CityData compose(City city, EnumDataObjectLoader<City, CityData> loader) {

      List<AppImage> imageList = loader.getAssociatedData(city, Component.IMAGE.name());
      return new CityData(null, city, city.getCountry(), imageList);
    }
  }

}
