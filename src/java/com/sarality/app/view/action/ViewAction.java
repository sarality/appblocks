package com.sarality.app.view.action;

import android.view.View;

import java.util.List;

/**
 * Interface for all Actions triggers by an event on a View
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewAction extends Action<ViewActionContext> {

  /**
   * @return Id of the view that triggers the action
   */
  public int getViewId();

  /**
   * @return Type of event that triggers the action
   */
  public TriggerType getTriggerType();

  /**
   * Sets up the action for the given view,
   *
   * @param view The view on which the action is being configured.
   */
  public void setupAction(View view);

  /**
   * Perform the action
   *
   * @param view       The View that triggered the action
   * @param trigger    Details about the event that triggered the action
   * @param viewDetail Details about the view that triggered the action
   */
  public boolean performAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail);

  /**
   * Register the set of actions that need to be performed before this action is
   * started
   *
   * @param triggeredAction The new action that will be need to be performed before current action is executed.
   */
  public void registerBeforeExecutionAction(ViewAction triggeredAction);

  /**
   * Register the set of actions that need to be performed after this action is
   * successfully completed
   *
   * @param triggeredAction The new action that will be performed once this action is complete.
   */
  public void registerOnSuccessAction(ViewAction triggeredAction);

  /**
   * Register the set of actions that need to be performed after this action if the action
   * has a failure in it
   *
   * @param triggeredAction The new action that will be performed once this action is complete.
   */
  public void registerOnFailureAction(ViewAction triggeredAction);

  /**
   * Returns the list of actions
   *
   * @return List of actions
   */
  public List<ViewAction> getActions();

}
