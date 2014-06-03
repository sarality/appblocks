package com.sarality.app.view.action;

import android.view.View;

/**
 * Interface for all Actions triggers by an event on a View
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewAction {

  /**
   * @return Id of the view that triggers the action
   */
  public int getViewId();

  /**
   * @return Type of event that triggers the action
   */
  public TriggerType getTriggerType();
  
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
   * Prepare the Action at the time of setup
   * 
   * @param view The View that would trigger the action
   * @param value The actual contents of the data on which the action would need to be performed
   * @return
   */
  public <T> void prepareAction(View view, T value);
  /**
   * Cloning the action for each item in the list
   * 
   * @param 
   * @return ViewAction a new instance should be created 
   */
  public ViewAction clone() throws CloneNotSupportedException;
}
