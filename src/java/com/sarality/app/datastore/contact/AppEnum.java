package com.sarality.app.datastore.contact;

/**
 * Stores different types of apps that need information from ContactDataStore
 *
 * @author sunayna (Sunayna Uberoy)
 */
public enum AppEnum {
  WHATSAPP("vnd.android.cursor.item/vnd.com.whatsapp.profile");

  private final String mimeType;

  AppEnum(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getMimeType() {
    return mimeType;
  }
}
