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
    NONE,
    APP_RESOURCE,
    URL,
    LOCAL_FILE,
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
}
