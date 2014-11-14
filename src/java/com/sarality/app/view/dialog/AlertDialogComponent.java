package com.sarality.app.view.dialog;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.sarality.app.view.action.ComponentActionManager;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.list.ListComponentAdapter;
import com.sarality.app.view.list.ListRowRenderer;

/**
 * Options for setting up the snooze
 * 
 * @author sunayna@ (Sunayna Uberoy)
 * @param <T>
 */
public class AlertDialogComponent<T> {
  private static List<ViewAction> rowActionList = new ArrayList<ViewAction>();
  private final Context context;
  private AlertDialog dialog;

  // List of actions to be setup on each row in the List.
  private List<ViewAction> actionList = new ArrayList<ViewAction>();
  private ListRowRenderer<T> dialogRenderer;
  private List<T> viewOptions;
  private String title;
  private final int listViewId;
  private final int dialogLayout;

  /**
   * Constructor.
   * 
   * @param context Context of the activity starting it.
   * @param date Activation Date to be changed once the user has selected one of the options
   * @param snoozeAction Reference to the caller.
   */
  public AlertDialogComponent(Context context, int dialogLayout, int listViewId) {
    this.context = context;
    this.dialogLayout = dialogLayout;
    this.listViewId = listViewId;
  }

  public void setRenderer(ListRowRenderer<T> dialogRenderer) {
    this.dialogRenderer = dialogRenderer;
  }

  /**
   * Registers the actions that would be setup on individual items within the Dialog
   * 
   * @param action The actual action that would be registered with the Dialog
   */
  public void registerAction(ViewAction action) {
    actionList.add(action);
  }

  /**
   * Registers the actions that would be setup on individual items within the Dialog
   * 
   * @param action The actual action that would be registered with the Dialog
   */
  public void registerRowAction(ViewAction action) {
    rowActionList.add(action);
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
  public void init(List<T> viewOptions) {
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
    final View dialogView = factory.inflate(dialogLayout, null);
    ListView listView = (ListView) dialogView.findViewById(listViewId);
    ComponentActionManager componentActionManager = new ComponentActionManager(rowActionList);

    ListComponentAdapter<T> adapter = new ListComponentAdapter<T>(context, dialogRenderer, viewOptions,
        componentActionManager);
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
