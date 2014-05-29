package com.sarality.app.view.action;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchActionPerformer implements OnTouchListener {

  private final String TAG = "ClickActionPerformer";

  private final ViewAction action;

  public TouchActionPerformer(ViewAction action) {
    this.action = action;
  }

  @Override
  public boolean onTouch(View view, MotionEvent event) {
    if (action.getEvent() == InputEvent.TOUCH_DOWN && event.getAction() != MotionEvent.ACTION_DOWN) {
      return false;
    }
    if (action.getEvent() == InputEvent.TOUCH_UP && event.getAction() != MotionEvent.ACTION_UP) {
      return false;
    }
    Log.d(TAG, "Processing Touch event for view with Id " + view.getId() + " and view " + view);
    return action.performAction(view, new ViewActionDetail(event), new ViewDetail(null));
  }

  public void setupListener(View view) {
    view.setOnTouchListener(this);
  }
}