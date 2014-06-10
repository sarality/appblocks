package com.sarality.app.view.action;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;
import com.sarality.app.view.dialog.AlertDialogComponent;

import android.app.Activity;
import android.view.View;

/**
 * Action that would snooze a reminder.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DisplayAlertDialogAction<T> extends BaseViewAction<T> implements ViewAction<T> {
  private T data;
  private final AlertDialogComponent<T> alertDialog;
  private final Activity context;

  /**
   * Constructor.
   * 
   * @param viewId
   *          Id of the View that triggers the action.
   * @param triggerType
   *          Type of event that triggers the action.
   */
  public DisplayAlertDialogAction(Activity context, int viewId, TriggerType triggerType,
      AlertDialogComponent<T> alertDialog) {
    super(viewId, triggerType);
    this.alertDialog = alertDialog;
    this.context = context;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    alertDialog.init(data);
    return true;
  }

  @Override
  public DisplayAlertDialogAction<T> cloneInstance() {
    DisplayAlertDialogAction<T> action = new DisplayAlertDialogAction<T>(context, this.getViewId(), this.getTriggerType(),
        this.alertDialog);
    return action;
  }

  @Override
  public void prepareView(View view, T input) {
    this.data = input;
   /* ReminderData.Builder builder = new ReminderData.Builder();
    data = builder.setReminderId(input.getReminderId()).setReminderType(input.getReminderType())
        .setActivityType(input.getActivityType()).setServiceType(input.getServiceType())
        .setServiceProvider(input.getServiceProvider()).setActivationDate(input.getActivationDate())
        .setActivityEndDate(input.getActivityEndDate()).setActivityStartDate(input.getActivityStartDate())
        .setExpirationDate(input.getExpirationDate()).build();*/
  }
}
