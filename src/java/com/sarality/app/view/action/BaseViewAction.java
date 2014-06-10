package com.sarality.app.view.action;

import java.util.List;

import android.view.View;

import com.sarality.app.common.collect.Lists;

/**
 * Base implementation for {@link ViewAction}.
 * <p>
 * Defines the Id of the View on which the Action is trigger and the type of
 * event that triggers the action.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T>
 *          The type of data that is used to setup the view which triggers the
 *          action.
 */
public abstract class BaseViewAction<T> implements ViewAction<T> {

  // The Id of the view that triggers the action
  private final int viewId;

  // The Type of event that triggers the action
  private final TriggerType triggerType;

  // List of actions to be performed after this action is complete
  private List<ViewAction<?>> actionList = Lists.of();

  /**
   * Constructor.
   * 
   * @param viewId
   *          Id of view that triggers the action.
   * @param event
   *          Type of event that triggers the action.
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
  public void registerAction(ViewAction<?> action) {
    // TODO separate out the Start/Success/Failure lists
    actionList.add(action);
  }

  @Override
  public List<ViewAction<?>> getActions() {
    return actionList;
  }

  public abstract boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail);

  @Override
  public final boolean performAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    try {
      // TODO onStart();
      boolean returnValue = doAction(view, actionDetail, viewDetail);
      onSuccess(view, actionDetail, viewDetail);
      return returnValue;
    } catch (Throwable t) {
      // TODO onFailure();
    }
    return true;
  }

  /**
   * Perform the set of actions after the current action is completed
   * successfully The method will iterate through the list of actions that were
   * registered when this action was first created
   * 
   * @param view
   *          The View that triggered the action
   * @param trigger
   *          Details about the event that triggered the action
   * @param viewDetail
   *          Details about the view that triggered the action
   * @return
   */
  private void onSuccess(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    for (ViewAction<?> action : actionList) {
      ViewActionTrigger detail = new ViewActionTrigger(view, TriggerType.COMPLETE_SUCCESS,
          actionDetail.getMotionEvent());
      action.performAction(view, detail, viewDetail);
    }
  }
}
