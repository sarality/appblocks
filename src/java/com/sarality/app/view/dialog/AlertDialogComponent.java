package com.sarality.app.view.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.sarality.app.view.action.ComponentAction;
import com.sarality.app.view.action.ViewAction;

/**
 * Setting up the AlertDialog
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
  private DialogType dialogType;
  private int viewId;

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
  public AlertDialogComponent(Activity context) {
    this.context = context;
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
   * Return the Dialog Type
   * 
   * @return Dialog Type
   */
  public DialogType getDialogType() {
    return dialogType;
  }

  /**
   * Initializes the AlertDialog Sets up the view Sets up the actions on the
   * views within the Dialog Builds the Dialog Displays the Dialog
   * 
   * @param data
   *          Data to be passed to the action to act on
   */
  public void init(View view, T data) {

    switch (dialogType) {
      case DIALOG_LIST:
        // TODO Add support for lists
        break;
      case DIALOG_TEXT:
        showDialogWithText(view, data);
        break;
      case DIALOG_VIEW:
        showDialogWithView(data);
        break;
      default:
        return;
    }
    dialog.show();
  }

  /**
   * Display the Dialog if it was setup with a view
   * 
   * @param data
   *          Data to be used to set on the dialog
   */
  private void showDialogWithView(T data) {
    Log.d(TAG, "Show View Dialog");
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    LayoutInflater factory = LayoutInflater.from(context);
    final View content = factory.inflate(viewId, null);
    setupActions(content, data);
    builder.setView(content);
    builder.setCancelable(true);
    dialog = builder.create();
  }

  /**
   * Display the Dialog with plain text
   * 
   * @param view
   *          Reference view
   * @param data
   *          Data to be used to set on the dialog
   */
  private void showDialogWithText(View view, T data) {
    Log.d(TAG, "Show Text Dialog " + view.getId());
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setMessage(data.toString());
    builder.setPositiveButton("OK", null);
    dialog = builder.create();
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
    ComponentAction<T> componentAction = new ComponentAction<T>();
    componentAction.setupActions(actionList, dialogView, data);
  }

  /**
   * Dismisses the dialog
   */
  public void dismiss() {
    dialog.dismiss();
  }

  public AlertDialogComponent<T> setDialogType(DialogType dialogType) {
    this.dialogType = dialogType;
    return this;
  }

  public AlertDialogComponent<T> setLayout(int viewId) {
    this.viewId = viewId;
    return this;
  }
}
