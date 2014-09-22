package com.sarality.app.view.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;

public class ComponentActionManager {

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
      action.setupAction(layout);
    }
  }
}
