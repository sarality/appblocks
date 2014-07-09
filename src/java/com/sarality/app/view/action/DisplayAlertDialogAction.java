package com.sarality.app.view.action;

import java.util.List;
import java.util.Map.Entry;

import android.view.View;

import com.sarality.app.view.dialog.AlertDialogComponent;

/**
 * Action that would display an alert Dialog.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DisplayAlertDialogAction extends BaseViewAction implements ViewAction {
  private List<Entry<String, Object>> data = null;
  private final AlertDialogComponent alertDialog;
 
  /**
   * Constructor.
   * 
   * @param viewId
   *          Id of the View that triggers the action.
   * @param triggerType
   *          Type of event that triggers the action.
   */
  public DisplayAlertDialogAction(int viewId, TriggerType triggerType, AlertDialogComponent alertDialog) {
    super(viewId, triggerType);
    this.alertDialog = alertDialog;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    alertDialog.init(data);
    return true;
  }
}

