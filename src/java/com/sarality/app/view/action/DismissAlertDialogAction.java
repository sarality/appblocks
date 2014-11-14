package com.sarality.app.view.action;

import android.view.View;

import com.sarality.app.view.dialog.AlertDialogComponent;

/**
 * Action that would close the Dialog
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DismissAlertDialogAction<T> extends BaseViewAction implements ViewAction {

  private final AlertDialogComponent<T> alertDialog;

  /**
   * Constructor.
   * 
   * @param viewId Id of the View that triggers the action.
   * @param triggerType Type of event that triggers the action.
   */
  public DismissAlertDialogAction(int viewId, TriggerType triggerType, AlertDialogComponent<T> alertDialog) {
    super(viewId, triggerType);
    this.alertDialog = alertDialog;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    alertDialog.dismiss();
    return true;
  }
}
