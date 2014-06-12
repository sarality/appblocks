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
  private List<ViewAction<T>> onClickActionList;

  // Complete list of actions
  private List<ViewAction<T>> onLongClickActionList;

  // Complete list of actions
  private List<ViewAction<T>> onTouchActionList;

  /**
   * Constructor.
   * 
   * @param viewId
   *          Id of view that triggers the action.
   * @param event
   *          Type of event that triggers the action.
   */
  public CompositeViewAction(int viewId) {
    super(viewId, null);
    onClickActionList = new ArrayList<ViewAction<T>>();
    onLongClickActionList = new ArrayList<ViewAction<T>>();
    onTouchActionList = new ArrayList<ViewAction<T>>();
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
    switch (action.getTriggerType()) {
      case CLICK:
        onClickActionList.add(action);
        break;
      case LONG_CLICK:
        onLongClickActionList.add(action);
        break;
      case TOUCH:
      case TOUCH_DOWN:
      case TOUCH_UP:
        onTouchActionList.add(action);
        break;
      default:
        break;
    }
  }

  /**
   * Provides the list of actions for Click trigger type
   * 
   * @return List of actions on a view given the trigger type
   */
  public List<ViewAction<T>> getOnClickActionList() {
    return onClickActionList;
  }

  /**
   * Provides the list of actions for LongClick trigger type
   * 
   * @return List of actions on a view given the trigger type
   */
  public List<ViewAction<T>> getOnLongClickActionList() {
    return onLongClickActionList;
  }

  /**
   * Provides the list of actions for Touch trigger type
   * 
   * @return List of actions on a view given the trigger type
   */
  public List<ViewAction<T>> getOnTouchActionList() {
    return onTouchActionList;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail) {
    List<ViewAction<T>> actionList = null;
    switch (trigger.getTriggerType()) {
      case CLICK:
        actionList = getOnClickActionList();
        break;
      case LONG_CLICK:
        actionList = getOnLongClickActionList();
        break;
      case TOUCH:
      case TOUCH_DOWN:
      case TOUCH_UP:
        actionList = getOnTouchActionList();
        break;
      default:
        return false;
    }

    for (ViewAction<T> action : actionList) {
      action.performAction(view, trigger, viewDetail);
    }

    return true;
  }

  @Override
  public ViewAction<T> cloneInstance() {
    return new CompositeViewAction<T>(getViewId());
  }

  @Override
  public void prepareView(View view, T value) {
    // Do nothing
  }
}
