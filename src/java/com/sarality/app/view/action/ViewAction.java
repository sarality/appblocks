package com.sarality.app.view.action;

import java.util.List;

import android.view.View;

/**
 * Interface for all Actions triggers by an event on a View
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T>
 *          The type of data that is used to setup the View that triggers the
 *          action.
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
   * @param view
   *          The View that would trigger the action.
   * @param value
   *          The actual contents of the data on which the action would need to
   *          be performed.
   */
  public void prepareView(View view, T value);

  /**
   * Perform the action
   * 
   * @param view
   *          The View that triggered the action
   * @param trigger
   *          Details about the event that triggered the action
   * @param viewDetail
   *          Details about the view that triggered the action
   */
  public boolean performAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail);

  /**
   * Clones the instance of the Action
   * 
   * @return ViewAction a new instance should be created
   */
  public ViewAction<T> cloneInstance();

  /**
   * Register the set of actions that need to be performed before this action is
   * started
   * 
   * @param triggeredAction
   *          The new action that will be need to be performed before current acion is 
   *          executed. 
   */
  public void registerBeforeExecutionAction(ViewAction<?> triggeredAction);

  /**
   * Register the set of actions that need to be performed after this action is
   * successfully completed
   * 
   * @param triggeredAction
   *          The new action that will be performed once this action is
   *          complete.
   */
  public void registerOnSuccessAction(ViewAction<?> triggeredAction);

  /**
   * Register the set of actions that need to be performed after this action if the action
   * has a failure in it
   * 
   * @param triggeredAction
   *          The new action that will be performed once this action is
   *          complete.
   */
  public void registerOnFailureAction(ViewAction<?> triggeredAction);

  /**
   * Returns the list of actions
   * 
   * @return List of actions
   */
  public List<ViewAction<?>> getActions();

}
