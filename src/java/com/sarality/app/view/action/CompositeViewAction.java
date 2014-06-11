package com.sarality.app.view.action;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.sarality.app.base.registry.MultiValueRegistry;

/**
 * Creates a list of actions per view and sets up the observer for each of the
 * views
 * 
 * @author sunayna@dothat.in sunayna
 * 
 */
public class CompositeViewAction<T> extends MultiValueRegistry<View, ViewAction<T>> {

  // Complete list of actions
  private final List<ViewAction<T>> actionList;

  // Layout view
  private final View view;

  // EntryProvider creating a mapping between view and Action
  private ViewActionRegistry<T> viewActionregistry;

  /**
   * Constructor
   * 
   * @param view
   *          Layout view
   * @param actionList
   *          The actions that will be performed on each of the viewIds with in
   *          the layout
   */
  public CompositeViewAction(View view, List<ViewAction<T>> actionList) {
    this.actionList = actionList;
    this.view = view;
  }

  /**
   * Sets up the registry and Sets up the ActionPerformer on the view
   * 
   * @param data
   *          Custom data for each of the actions
   */
  public void setupActions(T data) {
    viewActionregistry = new ViewActionRegistry<T>();

    for (ViewAction<T> action : actionList) {
      View currentView = view.findViewById(action.getViewId());
      action.prepareView(currentView, data);
      viewActionregistry.register(currentView, action);
      setActionPerformer(currentView, action.getTriggerType());
    }
    super.register(viewActionregistry);
  }

  /**
   * Provides the list of actions for a view and trigger type
   * 
   * @param view
   *          View for each the action has been setup
   * @param triggerType
   *          Trigger on a view that would generate an action
   * @return List of actions on a view given the trigger type
   */
  public List<ViewAction<T>> lookup(View view, TriggerType triggerType) {
    List<ViewAction<T>> actionList = super.lookup(view);
    List<ViewAction<T>> returnList = new ArrayList<ViewAction<T>>();

    for (ViewAction<T> action : actionList) {
      if (action.getTriggerType() == triggerType) {
        returnList.add(action);
      }
    }
    return returnList;
  }

  /**
   * Sets up the ActionPerformer for each of the views
   * 
   * @param view
   *          View on which the actionPerformer would be set
   * @param trigger
   *          Type of action performer that would be set
   */
  private void setActionPerformer(View view, TriggerType trigger) {
    // TODO : to be completed once reviewed
    // Will need modifications to ActionPerformer constructors
    // Will setup observers for each of the trigger items
    // For eg.
    // switch(trigger){
    // case CLICK:
    // new ClickActionPerformer<T>(this).setupListener(view);
  }
}

/**
 * Mapping between View and ViewAction
 * 
 * @author sunayna@dothat.in sunayna
 * 
 */
class ViewActionRegistry<T> extends MultiValueRegistry.EntryProvider<View, ViewAction<T>> {

  /**
   * Adds entry into registry for a view.
   * 
   * @param view
   *          Key in the registry
   * @param action
   *          Value in the registry : List of actions
   */
  protected void register(View view, ViewAction<T> action) {
    addEntry(view, action);
  }
}
