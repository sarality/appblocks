package com.sarality.app.view.action;

import android.view.View;

public class SupressEventAction extends BaseUserAction {

  public SupressEventAction(int viewId, InputEvent event) {
    super(viewId, event);
  }

  @Override
  public boolean performAction(View view, ViewActionDetail actionDetail, ViewDetail viewDetail) {
    // Do nothing. Just eat up the event
    return true;
  }
}
