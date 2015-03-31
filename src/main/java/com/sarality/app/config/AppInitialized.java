package com.sarality.app.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppInitialized implements AppStateValue<Boolean> {

  private static final String APP_INITIALIZED_FIELD = "APP_INITIALIZED_FIELD";
  private Context context;
  
  public AppInitialized(Context context) {
    this.context = context;
  }

  @Override
  public Boolean getValue() {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    return preferences.getBoolean(APP_INITIALIZED_FIELD, false);
  }

  @Override
  public boolean isEditable() {
    return true;
  }
  
  @Override
  public void setValue(Boolean value) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean(APP_INITIALIZED_FIELD, value);
    editor.commit();
  }
}
