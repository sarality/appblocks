package com.sarality.app.view.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sarality.app.view.action.ViewAction;

/**
 * Options for setting up the snooze
 * 
 * @author sunayna@ (Sunayna Uberoy)
 * @param <T>
 */
public class AlertDialogComponent<T> {
  private final Activity context;
  private AlertDialog dialog;

  // List of actions to be setup on each row in the List.
  private List<ViewAction<T>> actionList = new ArrayList<ViewAction<T>>();
  private final DialogRenderer<?> dialogRenderer;
  private Map<String, ?> viewOptions;
  private String title;

  /**
   * Constructor.
   * 
   * @param context Context of the activity starting it.
   * @param date Activation Date to be changed once the user has selected one of the options
   * @param snoozeAction Reference to the caller.
   */
  public AlertDialogComponent(Activity context, DialogRenderer<?> dialogRenderer) {
    this.context = context;
    this.dialogRenderer = dialogRenderer;
  }

  /**
   * Registers the actions that would be setup on individual items within the Dialog
   * 
   * @param action The actual action that would be registered with the Dialog
   */
  public void registerAction(ViewAction<T> action) {
    actionList.add(action);
  }

  /**
   * Set the title of the dialog
   * 
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Initializes the AlertDialog Sets up the view Sets up the actions on the views within the Dialog Builds the Dialog
   * Displays the Dialog
   * 
   * @param data Data to be passed to the action to act on
   * @param refreshListAction
   */
  public void init(Map<String, ?> viewOptions) {
    this.viewOptions = viewOptions;
    setDialog(setupListView());
  }

  /**
   * Setting up the list view
   * 
   * @param data
   * @param refreshListAction
   * @return
   */
  private View setupListView() {
    LayoutInflater factory = LayoutInflater.from(context);
    final View dialogView = factory.inflate(dialogRenderer.getDialogLayout(), null);
    ListView listView = (ListView) dialogView.findViewById(dialogRenderer.getListView());

    final ArrayList<String> list = new ArrayList<String>();
    for (Entry<String, ?> option : viewOptions.entrySet()) {
      list.add(option.getKey());
      dialogRenderer.setupActions(dialogView, option.getValue());
    }
    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, dialogRenderer.getLayout(),
        dialogRenderer.getTextView(), list);
    listView.setAdapter(adapter);
    return dialogView;

  }

  /**
   * 
   * @param dialogView
   */
  private void setDialog(View dialogView) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setView(dialogView);
    builder.setCancelable(true);
    // TODO add cancel/ok
    builder.setNegativeButton("CANCEL", null);
    builder.setTitle(title);
    dialog = builder.create();
    dialog.show();
  }

  /**
   * Dismisses the dialog
   */
  public void dismiss() {
    dialog.dismiss();
  }

}
