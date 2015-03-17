package com.sarality.app.keyboard;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/**
 * Detects if the System keyboard is displayed and notifies the listener of the visibility state change.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class SoftKeyboardDetector {

  private final View activityRootView;
  private SoftKeyboardVisibilityListener listener = null;

  public SoftKeyboardDetector(View activityRootView) {
    this.activityRootView = activityRootView;
  }

  public void setKeyboardVisibilityListener(SoftKeyboardVisibilityListener listener) {
    this.listener = listener;
  }

  public void detect() {
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
          if (listener != null) {
            listener.onKeyboardVisible();
          }
        }
      }
    });
  }
}
