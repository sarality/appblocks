package com.sarality.app.view.action;

import android.view.MotionEvent;

public class ViewActionDetail {
  private final MotionEvent event;
  
  public ViewActionDetail(MotionEvent event) {
    this.event = event;
  }

  public MotionEvent getMotionEvent() {
    return event;
  }
}
