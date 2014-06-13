package com.sarality.app.view.action;

import android.util.Log;
import android.view.View;

import com.sarality.app.view.dialog.AlertDialogComponent;

/**
 * Action that would close the Dialog
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DismissAlertDialogAction<T> extends BaseViewAction<T> implements ViewAction<T> {
  private final AlertDialogComponent<T> alertDialog;

  /**
   * Constructor.
   * 
   * @param viewId
   *          Id of the View that triggers the action.
   * @param triggerType
   *          Type of event that triggers the action.
   */
  public DismissAlertDialogAction(int viewId, TriggerType triggerType, AlertDialogComponent<T> alertDialog) {
    super(viewId, triggerType);
    this.alertDialog = alertDialog;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    Log.d("DismissAlertDialogAction", "I dismissed it!!");
    alertDialog.dismiss();
    return true;
  }

  @Override
  public void prepareView(View view, T input) {
    // Do nothing
  }

  @Override
  public ViewAction<T> cloneInstance() {
    return new DismissAlertDialogAction<T>(getViewId(), getTriggerType(), alertDialog);
  }
}
