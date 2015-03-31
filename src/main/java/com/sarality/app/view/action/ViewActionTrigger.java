package com.sarality.app.view.action;

import android.view.MotionEvent;
import android.view.View;

/**
 * Event that triggers an action
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewActionTrigger {
  // View that triggered the event.
  private final View view;
  // The type of trigger
  private final TriggerType triggerType;
  // Details about the event, if provided by Android.
  private final MotionEvent event;
  
  public ViewActionTrigger(View view, TriggerType triggerType, MotionEvent event) {
    this.view = view;
    this.triggerType = triggerType;
    this.event = event;
  }

  /**
   * @return View that triggered the event.
   */
  public final View View() {
    return view;
  }

  /**
   * @return Type of trigger for the event
   */
  public final TriggerType getTriggerType() {
    return triggerType;
  }

  /**
   * @return Details about the event, if provided by Android.
   */
  public final MotionEvent getMotionEvent() {
    return event;
  }
}
