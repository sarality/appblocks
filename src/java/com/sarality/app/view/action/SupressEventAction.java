package com.sarality.app.view.action;

import android.view.View;

public class SupressEventAction extends BaseViewAction {

  public SupressEventAction(int viewId, TriggerType event) {
    super(viewId, event);
  }

  @Override
  public boolean performAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    // Do nothing. Just eat up the event
    return true;
  }
}
