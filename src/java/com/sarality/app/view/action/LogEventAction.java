package com.sarality.app.view.action;

import android.util.Log;
import android.view.View;

/**
 * Action that simply logs the event.
 * <p>
 * Good for testing purposes.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class LogEventAction extends BaseViewAction implements ViewAction{

  // Tag to be used for Logging
  private static final String TAG = "LogEventAction";
  
  /**
   * Constructor.
   * 
   * @param viewId Id of the View that triggers the action.
   * @param triggerType Type of event that triggers the action.
   */
  public LogEventAction(int viewId, TriggerType triggerType) {
    super(viewId, triggerType);
  }

  @Override
  public boolean performAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    Log.d(TAG, "Performed Action for event on View with Id " + view.getId() + " and view " + view);
    return true;
  }

  public ViewAction clone() throws CloneNotSupportedException{
     return this;
  }

  @Override
  public <T> void prepareAction(View view, T value) {
    // No Setup required
  }
  
}
