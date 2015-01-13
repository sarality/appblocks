package com.sarality.app.keyboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Hides the default soft keyboard for the given activity.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class SoftKeyboardHider {

  public static void hideSoftKeyboard(Activity activity) {
    InputMethodManager mgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    View currentView = activity.getCurrentFocus();
    if (currentView != null) {
      mgr.hideSoftInputFromWindow(currentView.getWindowToken(), 0);
    }
  }
}
