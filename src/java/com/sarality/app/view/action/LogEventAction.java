package com.sarality.app.view.action;

import android.util.Log;
import android.view.View;

/**
 * Action that simply logs the event.
 * <p>
 * Good for testing purposes.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of data that is used to setup the view which triggers the looging of the event.
 */
public class LogEventAction<T> extends BaseViewAction<T> implements ViewAction<T> {

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
  public void prepareView(View view, T value) {
    // No Setup required
  }
  
  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    Log.d(TAG, "Performed Action for event on View with Id " + view.getId() + " and view " + view);
    return true;
  }

  public ViewAction<T> cloneInstance() {
    return new LogEventAction<T>(getViewId(), getTriggerType());
  }

}
