package com.sarality.app.data.image;

/**
 * An image that is displayed in the Application.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class AppImage {
  private final Integer resourceId;
  private final String url;
  private final String filename;
  private final ImageSource imageSource;

  public enum ImageSource {
    NONE, // No Image specified.
    APP_RESOURCE, // An image resource defined in the App itself
    URL, // Url to load the image from
    LOCAL_FILE, // Image cached on local drive, used in conjunction with URL
  }

  public AppImage(Integer resourceId, String url, String filename) {
    this.resourceId = resourceId;
    this.url = url;
    this.filename = filename;
    if (resourceId != null) {
      imageSource = ImageSource.APP_RESOURCE;
    } else if (filename != null) {
      imageSource = ImageSource.LOCAL_FILE;
    } else if (url != null) {
      imageSource = ImageSource.URL;
    } else {
      imageSource = ImageSource.NONE;
    }
  }

  public final Integer getResourceId() {
    return resourceId;
  }

  public final String getUrl() {
    return url;
  }

  public final String getFilename() {
    return filename;
  }

  public ImageSource getImageSource() {
    return imageSource;
  }
}
