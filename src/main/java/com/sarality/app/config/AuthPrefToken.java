package com.sarality.app.config;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Saves and allows edits on SharedPreferences for Token
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public class AuthPrefToken implements AppStateValue<String> {

  public final static String SHARED_PREF_AUTH_KEY = "DO_THAT_ACCOUNT_PREF";
  public final static String SHARED_PREF_AUTH_TOKEN = "DO_THAT_ACCOUNT_PREF_TOKEN";
  private Context context;

  public AuthPrefToken(Context context) {
    this.context = context;
  }

  @Override
  public String getValue() {
    SharedPreferences pref = context.getApplicationContext().getSharedPreferences(SHARED_PREF_AUTH_KEY,
        Activity.MODE_PRIVATE);
    return pref.getString(SHARED_PREF_AUTH_TOKEN, null);
  }

  @Override
  public boolean isEditable() {
    return true;
  }

  @Override
  public void setValue(String value) {
    SharedPreferences pref = context.getApplicationContext().getSharedPreferences(SHARED_PREF_AUTH_KEY,
        Activity.MODE_PRIVATE);
    SharedPreferences.Editor edit = pref.edit();
    edit.putString(SHARED_PREF_AUTH_TOKEN, value).commit();
  }

  public void clear(){
    SharedPreferences pref = context.getApplicationContext().getSharedPreferences(SHARED_PREF_AUTH_KEY,
        Activity.MODE_PRIVATE);
    SharedPreferences.Editor edit = pref.edit();
    edit.clear().commit();
  }

  public void remove(){
    SharedPreferences pref = context.getApplicationContext().getSharedPreferences(SHARED_PREF_AUTH_KEY,
        Activity.MODE_PRIVATE);
    SharedPreferences.Editor edit = pref.edit();
    edit.remove(SHARED_PREF_AUTH_TOKEN);
    edit.commit();
  }
}
