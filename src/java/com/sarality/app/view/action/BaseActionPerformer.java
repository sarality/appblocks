package com.sarality.app.view.action;

import java.util.List;

import android.util.Log;
import android.view.View;

/**
 * Base implementation for classes that setup the trigger type specific
 * listeners and make the call to perform the action when the event is triggered.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of data that is used to setup the view on which the action is being performed.
 */
public abstract class BaseActionPerformer<T> {
  
  private static final String TAG = "BaseActionPerformer";

  // The list of actions that needs to be performed
  private List<ViewAction<T>> actionList;

  // The action that needs to be performed
   private ViewAction<T> action;

 
  /**
   * Constructor.
   * 
   * @param actionList Actions that need to be performed.
   */
  public BaseActionPerformer(List<ViewAction<T>> actionList) {
    this.actionList = actionList;
  }

  /**
   * Constructor.
   * 
   * @param action Action that needs to be performed.
   */
  public BaseActionPerformer(ViewAction<T> action) {
   this.action = action;
  }
  
  /**
   * @return Action that needs to be performed.
   */
  protected ViewAction<T> getAction() {
    return action;
  }

  /**
   * @return Actions that need to be performed.
   */
  protected List<ViewAction<T>> getActionList() {
    return actionList;
  }
  
  /**
   * Indicated whether the listener is being setup of the same
   * view that was defined in the ViewAction.
   *
   * @param view The view that the listener will be set on.
   * @return {@code true} if it is the excepted view, {@code false} otherwise.
   */
  public boolean isValidListenerView(View view) {
    if (view == null || view.getId() != action.getViewId()) {
      String message = "Trying to setup listener on view with Id "
          + view.getId() + " while the action specifies the view id " + action.getViewId();
      IllegalArgumentException exception = new IllegalArgumentException(message);
      Log.e(TAG, message, exception);
      throw exception;
    }
    return true;
  }

  /**
   * Setup the appropriate listener on the given view.
   * 
   * @param view The view on which the listener needs to be set.
   */
  public abstract void setupListener(View view);
}
