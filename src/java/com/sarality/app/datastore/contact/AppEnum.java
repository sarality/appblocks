package com.sarality.app.datastore.contact;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores different types of apps that need information from ContactDataStore
 *
 * @author sunayna (Sunayna Uberoy)
 */
public enum AppEnum {
  WHATSAPP("vnd.android.cursor.item/vnd.com.whatsapp.profile");
  // Reverse-lookup map for getting the app name from mimeType
  private static Map<String, AppEnum> appEnumLookup = null;
  private final String mimeType;


  AppEnum(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getMimeType() {
    return mimeType;
  }

  public static AppEnum getAppEnum(String mimeType) {
    if (appEnumLookup == null) {
      initAppEnum();
    }

    return appEnumLookup.get(mimeType);
  }

  private static void initAppEnum() {
    appEnumLookup = new HashMap<String, AppEnum>();

    for (AppEnum appEnum : AppEnum.values()) {
      appEnumLookup.put(appEnum.getMimeType(), appEnum);
    }
  }
}
