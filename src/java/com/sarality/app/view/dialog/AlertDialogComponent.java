package com.sarality.app.view.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sarality.app.view.action.ClickActionPerformer;
import com.sarality.app.view.action.LongClickActionPerformer;
import com.sarality.app.view.action.TouchActionPerformer;
import com.sarality.app.view.action.TriggerType;
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
  public AlertDialogComponent(Activity context, DialogType dialogType) {
    this.context = context;
    this.dialogType = dialogType;
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

  public DialogType getDialogType(){
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
    // AlertDialog.Builder builder = new AlertDialog.Builder(context);
    // LayoutInflater factory = LayoutInflater.from(context);
    // final View content = factory.inflate(viewId, null);

    switch(dialogType){
      case DIALOG_LIST:
        break;
      case DIALOG_TEXT:
        break;
      case DIALOG_VIEW:
        setupActions(view, data);
        break;
      default:
        break;
      
    }
     
    // builder.setView(content);
    // builder.setCancelable(true);
    // dialog = builder.create();
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
      View view = context.findViewById(action.getViewId());
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

  public void ShowMessageDialog(String message) {
    ShowDialog(message, DialogButton.OK, new String[] {}, 0);
  }

  public void ShowMessageDialog(String message, DialogButton type) {
    ShowDialog(message, type, new String[] {}, 0);
  }

  public void ShowListDialog(String message, String[] listItems) {
    ShowDialog(message, DialogButton.OK_CANCEL, listItems, 0);
  }

  public AlertDialogComponent<T> ShowListDialog(String message, DialogButton type,  int viewId) {
    return ShowDialog(message, type, null, viewId);
  }
  
  private AlertDialogComponent<T> ShowDialog(String message, DialogButton type, String[] listItems, int viewId) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    
    if(dialogType == DialogType.DIALOG_VIEW){
       LayoutInflater factory = LayoutInflater.from(context);
       final View content = factory.inflate(viewId, null);
       builder.setView(content);
       builder.setCancelable(true);
       dialog = builder.create();
    }
    
    /*
    List CheckedItems;
    if (listItems.length > 0 && isMultiChoice == false) {
      CheckedItems = new ArrayList();// won't be used in this case.
      builder.setTitle(message);
      builder.setItems(listItems, selectedItemListener);
    } else if (listItems.length > 0 && isMultiChoice == true) {
      CheckedItems = new ArrayList();
      builder.setTitle(message);
      builder.setMultiChoiceItems(listItems, null, new OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean checked) {
          if (checked)
          // CheckedItems.add(which);
          // else
          {
            // if (CheckedItems.contains(which))
            // CheckedItems.remove(which);
          }
        }
      });
    } else {
      builder.setTitle("Milk supply tracker");
      builder.setMessage(message);
    }
    if (listItems.length == 0 || isMultiChoice) {
      switch (type) {
        case OK:
          builder.setPositiveButton("OK", listener);
          break;
        case OK_CANCEL:
          builder.setPositiveButton("OK", listener);
          builder.setNegativeButton("Cancel", listener);
          break;
        case YES_NO:
          builder.setPositiveButton("Yes", listener);
          builder.setNegativeButton("No", listener);
          break;
      }
    }
    builder.create().show();*/
    
    return this;
  }
}
