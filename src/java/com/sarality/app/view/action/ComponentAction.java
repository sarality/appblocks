package com.sarality.app.view.action;

import java.util.List;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;

public class ComponentAction<T> {

  // Tag for Logging
  private static final String TAG = "ComponentAction";

  // Map of View ID and compositeView Action
  SparseArray<CompositeViewAction<T>> viewMap = new SparseArray<CompositeViewAction<T>>();

  /**
   * Creates a new entry into the sparseArray. if an entry is already made then
   * only the action needs to be added to the compositeViewAction
   * 
   * @param viewId
   *          View Id
   * @param action
   *          View action
   */
  public void addViewAction(int viewId, ViewAction<T> action) {
    CompositeViewAction<T> compositeAction = viewMap.get(viewId);
     if ( compositeAction == null) {
      compositeAction = new CompositeViewAction<T>(viewId);
      viewMap.put(viewId, compositeAction);
    }  
    compositeAction.registerAction(action);
  }

  /**
   * Sets up the actions for each of the views on the layout
   * 
   * @param actionList
   *          a comprehensive list of all the actions for the layout
   * @param layout
   *          the layout itself
   * @param value
   *          Custom data to be set for the action
   */
  public void setupActions(List<ViewAction<T>> actionList, View layout, T value) {
    for (ViewAction<T> action : actionList) {
      View view = layout.findViewById(action.getViewId());
      if (view == null) {
        String message = "Invalid Configuration for " + action.getTriggerType() + " Event. No View with Id "
            + action.getViewId() + " found in row " + layout.getId();
        IllegalStateException exception = new IllegalStateException(message);
        Log.e(TAG, message, exception);
        throw exception;
      }
      action.prepareView(view, value);
      addViewAction(action.getViewId(), action);
    }
    setActionPerformer(layout);
  }

  /**
   * Sets up the Action performer from the list of actions
   * 
   * @param layout
   *          layout view on which the actions are set
   */
  private void setActionPerformer(View layout) {
    for (int i = 0; i < viewMap.size(); i++) {
      CompositeViewAction<T> compositeAction = viewMap.valueAt(i);

      View view = layout.findViewById(compositeAction.getViewId());
      if (!compositeAction.getOnClickActionList().isEmpty()) {
        new ClickActionPerformer<T>(compositeAction).setupListener(view);
      } else if (!compositeAction.getOnLongClickActionList().isEmpty()) {
        new LongClickActionPerformer<T>(compositeAction).setupListener(view);
      } else if (!compositeAction.getOnLongClickActionList().isEmpty()) {
        new TouchActionPerformer<T>(compositeAction).setupListener(view);
      }
    }
  }
}
