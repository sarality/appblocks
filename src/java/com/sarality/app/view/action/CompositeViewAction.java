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
public class CompositeViewAction<T> extends BaseViewAction<T> implements ViewAction<T> {

  // Complete list of actions
  private List<ViewAction<T>> actionList;

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
    actionList = new ArrayList<ViewAction<T>>();
  }

  /**
   * Register the set of actions that need to be performed before this action is
   * started
   * 
   * @param triggeredAction
   *          The new action that will be need to be performed before current
   *          action is executed.
   */
  public void registerAction(ViewAction<T> action) {
    actionList.add(action);
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail) {
    for (ViewAction<T> action : actionList) {
      action.performAction(view, trigger, viewDetail);
    }
    return true;
  }

  @Override
  public ViewAction<T> cloneInstance() {
    CompositeViewAction<T> newCompositeAction =  new CompositeViewAction<T>(getViewId(), getTriggerType());
    for (ViewAction<T> action : actionList) {
      newCompositeAction.registerAction(action.cloneInstance());
    }
    return newCompositeAction;
  }

  @Override
  public void prepareView(View view, T value) {
    for (ViewAction<T> action : actionList) {
      action.prepareView(view, value);
    }
  }
}
