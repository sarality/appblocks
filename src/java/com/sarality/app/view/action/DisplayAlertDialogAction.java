package com.sarality.app.view.action;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;
import com.sarality.app.view.dialog.AlertDialogComponent;

import android.view.View;

/**
 * Action that would display an alert Dialog.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DisplayAlertDialogAction<T> extends BaseViewAction<T> implements ViewAction<T> {
  private T data;
  private final AlertDialogComponent<T> alertDialog;

  /**
   * Constructor.
   * 
   * @param viewId
   *          Id of the View that triggers the action.
   * @param triggerType
   *          Type of event that triggers the action.
   */
  public DisplayAlertDialogAction(int viewId, TriggerType triggerType, AlertDialogComponent<T> alertDialog) {
    super(viewId, triggerType);
    this.alertDialog = alertDialog;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
   // alertDialog.init(view, data);
    return true;
  }

  @Override
  public DisplayAlertDialogAction<T> cloneInstance() {
    DisplayAlertDialogAction<T> action = new DisplayAlertDialogAction<T>(this.getViewId(), this.getTriggerType(),
        this.alertDialog);
    return action;
  }

  @Override
  public void prepareView(View view, T input) {
    this.data = input;
  }
}
