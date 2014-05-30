package com.sarality.app.view.action;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Performs the Action when the given view is touched.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class TouchActionPerformer extends BaseActionPerformer implements OnTouchListener {

  /**
   * Constructor 
   * 
   * @param action ViewAction that needs to be performed on touch.
   */
  public TouchActionPerformer(ViewAction action) {
    super(action);
  }

  @Override
  public boolean onTouch(View view, MotionEvent event) {
    if (getAction().getTriggerType() == TriggerType.TOUCH_DOWN && event.getAction() != MotionEvent.ACTION_DOWN) {
      return false;
    }
    if (getAction().getTriggerType() == TriggerType.TOUCH_UP && event.getAction() != MotionEvent.ACTION_UP) {
      return false;
    }
    return getAction().performAction(view, new ViewActionTrigger(view, getAction().getTriggerType(), event),
        new ViewDetail(view, null));
  }

  @Override
  public void setupListener(View view) {
    if (isValidListenerView(view)) {
      view.setOnTouchListener(this);
    }
  }
}
