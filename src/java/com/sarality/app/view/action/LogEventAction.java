package com.sarality.app.view.action;

import android.util.Log;
import android.view.View;

public class LogEventAction extends BaseUserAction implements ViewAction {

  private static final String TAG = "LogEventAction";
  
  public LogEventAction(int viewId, InputEvent event) {
    super(viewId, event);
  }

  @Override
  public boolean performAction(View view, ViewActionDetail actionDetail, ViewDetail viewDetail) {
    Log.d(TAG, "Performed Action for event on View with Id " + view.getId() + " and view " + view);
    return true;
  }
}
