package com.sarality.app.view.action;

import android.view.MotionEvent;
import android.view.View;

/**
 * Context for execution of ViewAction.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class AppViewActionContext {
  private final View view;
  private final MotionEvent motionEvent;

  public AppViewActionContext(View view) {
    this.view = view;
    this.motionEvent = null;
  }

  public AppViewActionContext(View view, MotionEvent motionEvent) {
    this.view = view;
    this.motionEvent = motionEvent;
  }

  public View getView() {
    return view;
  }

  public MotionEvent getMotionEvent() {
    return motionEvent;
  }
}
