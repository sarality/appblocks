package com.sarality.app.view.action;

import android.view.View;

/**
 * Interface for all Actions triggers by an event on a View
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of data that is used to setup the View that triggers the action.
 */
public interface ViewAction<T> {

  /**
   * @return Id of the view that triggers the action
   */
  public int getViewId();

  /**
   * @return Type of event that triggers the action
   */
  public TriggerType getTriggerType();

  /**
   * Prepare the View so that the Action can be performed on it later.
   * 
   * @param view The View that would trigger the action.
   * @param value The actual contents of the data on which the action would need to be performed.
   * @return
   */
  public void prepareView(View view, T value);
  
  /**
   * Perform the action
   * 
   * @param view The View that triggered the action
   * @param trigger Details about the event that triggered the action
   * @param viewDetail Details about the view that triggered the action
   * @return
   */
  public boolean performAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail);

  /**
   * Clones the instance of the Action
   * 
   * @return ViewAction a new instance should be created 
   */
  public ViewAction<T> cloneInstance();
}
