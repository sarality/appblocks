package com.sarality.app.view.action;

import android.view.View;

/**
 * Base implementation for {@link ViewAction}.
 * <p>
 * Defines the Id of the View on which the Action is trigger and
 * the type of event that triggers the action.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseViewAction implements ViewAction {

  // The Id of the view that triggers the action
  private final int viewId;

  // The Type of event that triggers the action
  private final TriggerType triggerType;
  
  protected View view; 

  /**
   * Constructor.
   * 
   * @param viewId Id of view that triggers the action.
   * @param event Type of event that triggers the action.
   */
  public BaseViewAction(int viewId, TriggerType triggerType) {
    this.viewId = viewId;
    this.triggerType = triggerType;
  }

  @Override
  public int getViewId() {
    return viewId;
  }

  @Override
  public final TriggerType getTriggerType() {
    return triggerType;
  }
  
  @Override
  public View getView(){
    return view;
  }
  
  @Override 
  public ViewAction clone() throws CloneNotSupportedException{
    throw new CloneNotSupportedException();
  }
}
