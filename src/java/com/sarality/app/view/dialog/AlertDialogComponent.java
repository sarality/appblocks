package com.sarality.app.view.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.sarality.app.view.action.ClickActionPerformer;
import com.sarality.app.view.action.LongClickActionPerformer;
import com.sarality.app.view.action.TouchActionPerformer;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;

/**
 * Options for setting up the snooze
 * 
 * @author sunayna@ (Sunayna Uberoy)
 * @param <T>
 */
public class AlertDialogComponent<T> {
  private static final String TAG = null;
  private final Activity context;
  private AlertDialog dialog;

  // List of actions to be setup on each row in the List.
  private List<ViewAction<T>> actionList = new ArrayList<ViewAction<T>>();
  private final int viewId;

  /**
   * Constructor.
   * 
   * @param context
   *          Context of the activity starting it.
   * @param date
   *          Activation Date to be changed once the user has selected one of
   *          the options
   * @param snoozeAction
   *          Reference to the caller.
   */
  public AlertDialogComponent(Activity context, int viewId) {
    this.context = context;
    this.viewId = viewId;
  }

  /**
   * Registers the actions that would be setup on individual items within the
   * Dialog
   * 
   * @param action
   *          The actual action that would be registered with the Dialog
   */
  public void registerAction(ViewAction<T> action) {
    actionList.add(action);
  }

  /**
   * Initializes the AlertDialog Sets up the view Sets up the actions on the
   * views within the Dialog Builds the Dialog Displays the Dialog
   * 
   * @param data
   *          Data to be passed to the action to act on
   */
  public void init(T data) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    LayoutInflater factory = LayoutInflater.from(context);
    final View content = factory.inflate(viewId, null);

    setupActions(content, data);

    builder.setView(content);
    builder.setCancelable(true);
    dialog = builder.create();
    dialog.show();
  }

  /**
   * Sets up the actions on the views
   * 
   * @param dialogView
   *          The dialog layout
   * @param data
   *          The data to be sent to the action to act on
   */
  public void setupActions(View dialogView, T data) {
    for (ViewAction<T> action : actionList) {
      ViewAction<T> clonedAction;
      View view = dialogView.findViewById(action.getViewId());
      if (view == null) {
        String message = "Invalid Configuration for " + action.getTriggerType() + " Event. No View with Id "
            + action.getViewId() + " found in row " + dialogView.getId();
        IllegalStateException exception = new IllegalStateException(message);
        Log.e(TAG, message, exception);
        throw exception;
      }

      clonedAction = action.cloneInstance();
      clonedAction.prepareView(view, data);
      TriggerType input = action.getTriggerType();
      if (input == TriggerType.CLICK) {
        new ClickActionPerformer<T>(clonedAction).setupListener(view);
      } else if (input == TriggerType.LONG_CLICK) {
        new LongClickActionPerformer<T>(clonedAction).setupListener(view);
      } else if (input == TriggerType.TOUCH || input == TriggerType.TOUCH_DOWN || input == TriggerType.TOUCH_UP) {
        new TouchActionPerformer<T>(clonedAction).setupListener(view);
      }
    }
  }

  /**
   * Dismisses the dialog
   */
  public void dismiss() {
    dialog.dismiss();
  }

}
