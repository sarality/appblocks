package com.sarality.app.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;

/**
 * Hides the default softkeyboard for the given view in the given activity.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class SystemKeyboardHider {

  private final View activityRootView;
  private final Activity activity;

  public SystemKeyboardHider(Activity activity, View activityRootView) {
    this.activity = activity;
    this.activityRootView = activityRootView;
  }

  public void configure() {
    activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        Rect frame = new Rect();
        // Populate it with the coordinates of the area of the view that is still visible.
        activityRootView.getWindowVisibleDisplayFrame(frame);

        // If there is a difference of greater than 50 pixels between the actual height of the View
        // and the visible portion of the view, then the keyboard is what is taking up all that space.
        int heightDiff = activityRootView.getRootView().getHeight() - (frame.bottom - frame.top);
        if (heightDiff > 50) { 
          hideSoftKeyboard();
        }
      }
    });
  }

  private void hideSoftKeyboard() {
    InputMethodManager mgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    View currentView = activity.getCurrentFocus();
    if (currentView != null) {
      mgr.hideSoftInputFromWindow(currentView.getWindowToken(), 0);
    }
  }
}
