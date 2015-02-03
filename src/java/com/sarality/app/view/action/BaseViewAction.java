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
 */
public abstract class BaseViewAction implements ViewAction, Action<ViewActionContext> {

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
   * @param triggerType Type of event that triggers the action.
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
   * @param view View on which the actions are set
   */
  private void setActionPerformer(View view) {
    if (triggerType == TriggerType.CLICK) {
      new ClickViewActionPerformer(this).setupListener(view);
    } else if (triggerType == TriggerType.LONG_CLICK) {
      new LongClickViewActionPerformer(this).setupListener(view);
    } else if (triggerType == TriggerType.TOUCH || triggerType == TriggerType.TOUCH_DOWN
        || triggerType == TriggerType.TOUCH_UP) {
      new TouchViewActionPerformer(this).setupListener(view);
    } else if (triggerType == TriggerType.CLICK_LIST_ITEM) {
      new ClickListViewItemActionPerformer(this).setupListener(view);
    }
  }

  public abstract boolean doAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail);

  @Override
  public final boolean performAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail) {
    try {
      runAction(beforeActionList, view, trigger, viewDetail);
      boolean returnValue = doAction(view, trigger, viewDetail);
      runAction(successActionList, view, trigger, viewDetail);
      return returnValue;
    } catch (Throwable t) {
      runAction(failureActionList, view, trigger, viewDetail);
    }
    return false;
  }

  @Override
  public void performAction(ViewActionContext actionContext) {
    performAction(actionContext.getView(), actionContext.getTrigger(), actionContext.getViewDetail());
  }

  /**
   * Perform the set of actions after the current action is completed successfully The method will iterate through the
   * list of actions that were registered when this action was first created
   * 
   * @param view The View that triggered the action
   * @param triggerDetail Details about the event that triggered the action
   * @param viewDetail Details about the view that triggered the action
   */
  private void runAction(List<ViewAction> actionList, View view, ViewActionTrigger triggerDetail,
      ViewDetail viewDetail) {
    for (ViewAction action : actionList) {
      ViewActionTrigger detail = new ViewActionTrigger(view, action.getTriggerType(), triggerDetail.getMotionEvent());
      action.performAction(view, detail, viewDetail);
    }
  }
}
