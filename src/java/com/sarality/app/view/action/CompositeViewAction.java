package com.sarality.app.view.action;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

/**
 * Similar to ViewAction but will have a list of actions for a given view
 * 
 * @author sunayna@dothat.in sunayna
 * 
 */
public class CompositeViewAction extends BaseViewAction implements ViewAction {

  // Complete list of actions
  private List<ViewAction> actionList;

  /**
   * Constructor.
   * 
   * @param viewId
   *          Id of view that triggers the action.
   * @param trigger
   * @param event
   *          Type of event that triggers the action.
   */
  public CompositeViewAction(int viewId, TriggerType trigger) {
    super(viewId, trigger);
    actionList = new ArrayList<ViewAction>();
  }

  /**
   * Register the set of actions that need to be performed before this action is
   * started
   * 
   * @param triggeredAction
   *          The new action that will be need to be performed before current
   *          action is executed.
   */
  public void registerAction(ViewAction action) {
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
