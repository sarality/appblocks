package com.sarality.app.view.action;

import android.view.View;

public interface ViewAction {
  
  public int getViewId();
  
  public InputEvent getEvent();
  
  public boolean performAction(View view, ViewActionDetail actionDetail, ViewDetail viewDetail);
}
