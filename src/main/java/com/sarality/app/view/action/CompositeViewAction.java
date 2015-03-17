package com.sarality.app.view.action;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;

/**
 * Collection of all the actions that need to be performed on the same view with the same triggerType
 * 
 * @author sunayna@dothat.in sunayna
 */
public class CompositeViewAction extends BaseViewAction implements ViewAction {

  private static final String TAG = "CompositeViewAction";
  // Complete list of actions
  private List<ViewAction> actionList;

  /**
   * Constructor.
   * 
   * @param viewId Id of view that triggers the action.
   * @param trigger
   * @param event Type of event that triggers the action.
   */
  public CompositeViewAction(int viewId, TriggerType trigger) {
    super(viewId, trigger);
    actionList = new ArrayList<ViewAction>();
  }

  /**
   * Register the set of actions that need to be performed before this action is started
   * 
   * @param triggeredAction The new action that will be need to be performed before current action is executed.
   */
  public void registerAction(ViewAction action) {
    if (action.getViewId() != this.getViewId() || action.getTriggerType() != this.getTriggerType()) {
      String message = "Invalid Action , cannot add TriggerType=" + action.getTriggerType()
          + " for CompositeView with Trigger Type= " + this.getTriggerType() + " and View Id ="
          + Integer.toHexString(action.getViewId()) + " CompositeView viewId= " + Integer.toHexString(this.getViewId());
      IllegalArgumentException exception = new IllegalArgumentException(message);
      Log.e(TAG, message, exception);
      throw exception;
    }
    actionList.add(action);
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail) {
    for (ViewAction action : actionList) {
      action.performAction(view, trigger, viewDetail);
    }
    return true;
  }
}
