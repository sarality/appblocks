package com.sarality.app.view.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import android.view.View;

public class ComponentActionManager<T> {

  // Tag for Logging
  private static final String TAG = "ComponentActionManager";

  // Map of View ID and compositeView Action
  private final Map<ViewTrigger, ViewAction<T>> viewMap;

  /**
   * Constructor
   * 
   * @param actionList Takes in the list of all the actions on a component and creates a mapping between <View,Trigger>
   *          and <List of actions>
   */
  public ComponentActionManager(List<ViewAction<T>> actionList) {
    this.viewMap = new HashMap<ViewTrigger, ViewAction<T>>();
    for (ViewAction<T> action : actionList) {
      addViewAction(action.getViewId(), action.getTriggerType(), action);
    }
  }

  /**
   * Creates a new entry into the Map. if an entry is already made then a composite action is created. If composite
   * action is already present then only the action needs to be added to the compositeViewAction
   * 
   * @param viewId View Id
   * @param trigger Trigger Type for that view
   * @param action View action
   */
  private void addViewAction(int viewId, TriggerType trigger, ViewAction<T> action) {
    ViewTrigger viewTrigger = new ViewTrigger(viewId, trigger);

    ViewAction<T> actionValue = viewMap.get(viewTrigger);
    if (actionValue == null) {
      viewMap.put(viewTrigger, action);
    } // TODO remove dependency on class
    else if (actionValue.getClass() == CompositeViewAction.class) {
      CompositeViewAction<T> compositeView = (CompositeViewAction<T>) actionValue;
      compositeView.registerAction(action);
    } else {
      CompositeViewAction<T> compositeAction = new CompositeViewAction<T>(viewId, trigger);
      compositeAction.registerAction(actionValue);
      compositeAction.registerAction(action);
      viewMap.put(viewTrigger, compositeAction);
    }
  }

  /**
   * Sets up the actions for each of the views on the layout
   * 
   * @param actionList a comprehensive list of all the actions for the layout
   * @param layout the layout itself
   * @param value Custom data to be set for the action
   */
  public void setupActions(View layout, T value) {
    for (ViewAction<T> action : viewMap.values()) {
      View view = layout.findViewById(action.getViewId());
      if (view == null) {
        String message = "Invalid Configuration for " + action.getTriggerType() + " Event. No View with Id "
            + action.getViewId() + " found in row " + layout.getId();
        IllegalStateException exception = new IllegalStateException(message);
        Log.e(TAG, message, exception);
        throw exception;
      }
      action.prepareView(view, value);
      setActionPerformer(view, action);
    }
  }

  /**
   * Sets up the Action performer for a particular kind of trigger
   * 
   * @param layout layout view on which the actions are set
   */
  private void setActionPerformer(View view, ViewAction<T> action) {
    if (action.getTriggerType() == TriggerType.CLICK) {
      new ClickActionPerformer<T>(action).setupListener(view);
    } else if (action.getTriggerType() == TriggerType.LONG_CLICK) {
      new LongClickActionPerformer<T>(action).setupListener(view);
    } else if (action.getTriggerType() == TriggerType.TOUCH || action.getTriggerType() == TriggerType.TOUCH_DOWN
        || action.getTriggerType() == TriggerType.TOUCH_UP) {
      new TouchActionPerformer<T>(action).setupListener(view);
    } else if (action.getTriggerType() == TriggerType.ITEM_CLICK) {
      new ListItemClickActionPerformer<T>(action).setupListener(view);
    }
  }
}
