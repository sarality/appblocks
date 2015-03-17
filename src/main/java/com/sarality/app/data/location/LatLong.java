package com.sarality.app.data.location;

/**
 * Data Object representing a Location as Latitude Longitude.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class LatLong {
  private final double latitude;
  private final double longitude;

  /**
   * Constructor.
   * 
   * @param latitude Latitude for the location.
   * @param longitude Longitude for the location.
   */
  public LatLong(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * @return Latitude value as a double.
   */
  public double getLat() {
    return latitude;
  }

  /**
   * @return Longitude value as a double.
   */
  public double getLong() {
    return longitude;
  }
}
