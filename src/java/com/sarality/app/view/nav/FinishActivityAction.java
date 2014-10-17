package com.sarality.app.view.nav;

import android.app.Activity;
import android.view.View;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Action to be finished
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class FinishActivityAction extends BaseViewAction {
 private final Activity activity;
  /**
   * Constructor.
   * 
   * @param viewId Id of view that triggers the action.
   * @param triggerType Type of event that triggers the action.
   * @param Activity that needs to be finished
   */
  public FinishActivityAction(int viewId, TriggerType triggerType, Activity activity) {
    super(viewId, triggerType);
    this.activity = activity;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    activity.finish();
    return true;
  }
}
