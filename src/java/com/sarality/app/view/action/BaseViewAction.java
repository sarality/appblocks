package com.sarality.app.view.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.view.View;

import com.sarality.app.common.collect.Lists;

/**
 * Base implementation for {@link ViewAction}.
 * <p>
 * Defines the Id of the View on which the Action is trigger and the type of event that triggers the action.
 * 
 * @author abhideep@ (Abhideep Singh)
 * @param <T> The type of data that is used to setup the view which triggers the action.
 */
public abstract class BaseViewAction implements ViewAction {

  private static final Logger logger = LoggerFactory.getLogger(BaseViewAction.class);
  
  // The Id of the view that triggers the action
  private final int viewId;

  // The Type of event that triggers the action
  private final TriggerType triggerType;

  // List of actions to be performed after this action is complete
  private List<ViewAction> successActionList = Lists.of();

  // List of actions to be performed after this action is a failure
  private List<ViewAction> failureActionList = Lists.of();

  // List of actions to be performed before the action is started
  private List<ViewAction> beforeActionList = Lists.of();

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
  public void registerBeforeExecutionAction(ViewAction action) {
    beforeActionList.add(action);
  }

  @Override
  public void registerOnSuccessAction(ViewAction action) {
    successActionList.add(action);
  }

  @Override
  public void registerOnFailureAction(ViewAction action) {
    failureActionList.add(action);
  }

  @Override
  public List<ViewAction> getActions() {
    List<ViewAction> completeActionList = new ArrayList<ViewAction>();
    completeActionList.addAll(successActionList);
    completeActionList.addAll(failureActionList);
    completeActionList.addAll(beforeActionList);
    return completeActionList;
  }

  @Override
  public void setupAction(View view) {
    View actionView = view.findViewById(getViewId());
    if (actionView == null) {
      String message = "Invalid Configuration for " + getTriggerType() + " Event. No View with Id "
          + Integer.toHexString(getViewId()) + " found in view " + Integer.toHexString(view.getId());
      IllegalStateException exception = new IllegalStateException(message);
      logger.error(message, exception);
      throw exception;
    }
    setActionPerformer(actionView);
  }

  /**
   * Sets up the Action performer for a particular kind of trigger
   * 
   * @param layout layout view on which the actions are set
   */
  private void setActionPerformer(View view) {
    if (triggerType == TriggerType.CLICK) {
      new ClickActionPerformer(this).setupListener(view);
    } else if (triggerType == TriggerType.LONG_CLICK) {
      new LongClickActionPerformer(this).setupListener(view);
    } else if (triggerType == TriggerType.TOUCH || triggerType == TriggerType.TOUCH_DOWN
        || triggerType == TriggerType.TOUCH_UP) {
      new TouchActionPerformer(this).setupListener(view);
    } else if (triggerType == TriggerType.CLICK_LIST_ITEM) {
      new ListItemClickActionPerformer(this).setupListener(view);
    }
  }

  public abstract boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail);

  @Override
  public final boolean performAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    try {
      runAction(beforeActionList, view, actionDetail, viewDetail);
      boolean returnValue = doAction(view, actionDetail, viewDetail);
      runAction(successActionList, view, actionDetail, viewDetail);
      return returnValue;
    } catch (Throwable t) {
      runAction(failureActionList, view, actionDetail, viewDetail);
    }
    return false;
  }

  /**
   * Perform the set of actions after the current action is completed successfully The method will iterate through the
   * list of actions that were registered when this action was first created
   * 
   * @param view The View that triggered the action
   * @param trigger Details about the event that triggered the action
   * @param viewDetail Details about the view that triggered the action
   * @return
   */
  private void runAction(List<ViewAction> actionList, View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    for (ViewAction action : actionList) {
      ViewActionTrigger detail = new ViewActionTrigger(view, action.getTriggerType(), actionDetail.getMotionEvent());
      action.performAction(view, detail, viewDetail);
    }
  }
}
