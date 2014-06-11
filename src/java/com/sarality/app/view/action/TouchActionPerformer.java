package com.sarality.app.view.action;

import java.util.List;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Performs the Action when the given view is touched.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T>
 *          The type of data that is used to setup the view on which the touch
 *          based action is being performed.
 */
public class TouchActionPerformer<T> extends BaseActionPerformer<T> implements OnTouchListener {

  /**
   * Constructor
   * 
   * @param list
   *          ViewAction that needs to be performed on touch.
   */
  public TouchActionPerformer(List<ViewAction<T>> list) {
    super(list);
  }

  @Override
  public boolean onTouch(View view, MotionEvent event) {
    for (ViewAction<T> action : getActionList()) {
      if (action.getTriggerType() == TriggerType.TOUCH_DOWN && event.getAction() != MotionEvent.ACTION_DOWN) {
        return false;
      }
      if (action.getTriggerType() == TriggerType.TOUCH_UP && event.getAction() != MotionEvent.ACTION_UP) {
        return false;
      }
      action.performAction(view, new ViewActionTrigger(view, getAction().getTriggerType(), event), new ViewDetail(view,
          null));
    }
    return true;
  }

  @Override
  public void setupListener(View view) {
    if (isValidListenerView(view)) {
      view.setOnTouchListener(this);
    }
  }
}
