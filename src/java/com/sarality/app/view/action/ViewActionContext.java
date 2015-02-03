package com.sarality.app.view.action;

import android.view.View;

/**
 * The context needed to execute an Action on a View.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewActionContext {

  private final View view;
  private final ViewActionTrigger trigger;
  private final ViewDetail viewDetail;

  /**
   * Constructor.
   *
   * @param view The View that the action is trigger on.
   * @param trigger The event that triggered the call to the action.
   * @param viewDetail Details about the view that triggered the event.
   */
  public ViewActionContext(View view, ViewActionTrigger trigger, ViewDetail viewDetail) {
    this.view = view;
    this.trigger = trigger;
    this.viewDetail = viewDetail;
  }

  public View getView() {
    return view;
  }

  public ViewActionTrigger getTrigger() {
    return trigger;
  }

  public ViewDetail getViewDetail() {
    return viewDetail;
  }
}
