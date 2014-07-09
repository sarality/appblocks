package com.sarality.app.view.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import android.view.View;

public class ComponentActionManager {

  // Tag for Logging
  private static final String TAG = "ComponentActionManager";

  // Map of View ID and compositeView Action
  private final Map<ViewTrigger, ViewAction> viewMap;

  /**
   * Constructor
   * 
   * @param actionList Takes in the list of all the actions on a component and creates a mapping between <View,Trigger>
   *          and <List of actions>
   */
  public ComponentActionManager(List<ViewAction> actionList) {
    this.viewMap = new HashMap<ViewTrigger, ViewAction>();
    for (ViewAction action : actionList) {
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
  private void addViewAction(int viewId, TriggerType trigger, ViewAction action) {
    ViewTrigger viewTrigger = new ViewTrigger(viewId, trigger);

    ViewAction actionValue = viewMap.get(viewTrigger);
    if (actionValue == null) {
      viewMap.put(viewTrigger, action);
    } // TODO remove dependency on class
    else if (actionValue.getClass() == CompositeViewAction.class) {
      CompositeViewAction compositeView = (CompositeViewAction) actionValue;
      compositeView.registerAction(action);
    } else {
      CompositeViewAction compositeAction = new CompositeViewAction(viewId, trigger);
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
  public void setupActions(View layout) {
    for (ViewAction action : viewMap.values()) {
      View view = layout.findViewById(action.getViewId());
      if (view == null) {
        String message = "Invalid Configuration for " + action.getTriggerType() + " Event. No View with Id "
            + Integer.toHexString(action.getViewId()) + " found in row " + Integer.toHexString(layout.getId());
        IllegalStateException exception = new IllegalStateException(message);
        Log.e(TAG, message, exception);
        throw exception;
      }
      setActionPerformer(view, action);
    }
  }

  /**
   * Sets up the Action performer for a particular kind of trigger
   * 
   * @param layout layout view on which the actions are set
   */
  private void setActionPerformer(View view, ViewAction action) {
    if (action.getTriggerType() == TriggerType.CLICK) {
      new ClickActionPerformer(action).setupListener(view);
    } else if (action.getTriggerType() == TriggerType.LONG_CLICK) {
      new LongClickActionPerformer(action).setupListener(view);
    } else if (action.getTriggerType() == TriggerType.TOUCH || action.getTriggerType() == TriggerType.TOUCH_DOWN
        || action.getTriggerType() == TriggerType.TOUCH_UP) {
      new TouchActionPerformer(action).setupListener(view);
    } else if (action.getTriggerType() == TriggerType.CLICK_LIST_ITEM) {
      new ListItemClickActionPerformer(action).setupListener(view);
    }
  }
}
