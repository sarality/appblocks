package com.sarality.app.config;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Saves and allows edits on SharedPreferences for UserId
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public class AuthPrefUserId implements AppStateValue<Long> {

  public final static String SHARED_PREF_AUTH_KEY = "DO_THAT_ACCOUNT_PREF";
  private Context context;

  public AuthPrefUserId(Context context) {
    this.context = context;
  }

  @Override
  public Long getValue() {
    SharedPreferences pref = context.getApplicationContext().getSharedPreferences(SHARED_PREF_AUTH_KEY,
        Activity.MODE_PRIVATE);
    AuthPrefEmail prefEmail = new AuthPrefEmail(context);
    return pref.getLong(prefEmail.getValue(), 0l);
  }

  @Override
  public void setValue(Long value) {
    SharedPreferences pref = context.getApplicationContext().getSharedPreferences(SHARED_PREF_AUTH_KEY,
        Activity.MODE_PRIVATE);
    SharedPreferences.Editor edit = pref.edit();
    AuthPrefEmail prefEmail = new AuthPrefEmail(context);
    edit.putLong(prefEmail.getValue(), value).commit();
  }

  @Override
  public boolean isEditable() {
    return true;
  }

  public void clear() {
    SharedPreferences pref = context.getApplicationContext().getSharedPreferences(SHARED_PREF_AUTH_KEY,
        Activity.MODE_PRIVATE);
    SharedPreferences.Editor edit = pref.edit();
    edit.clear().commit();
  }
}
