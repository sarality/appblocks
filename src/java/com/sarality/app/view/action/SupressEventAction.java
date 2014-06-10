package com.sarality.app.view.action;

import android.view.View;

/**
 * Action to suppress any events on a given View.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The type of data that is used to setup the view on which the event is supressed.
 */
public class SupressEventAction<T> extends BaseViewAction<T> {

  public SupressEventAction(int viewId, TriggerType event) {
    super(viewId, event);
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    // Do nothing. Just eat up the event
    return true;
  }

  @Override
  public void prepareView(View view, T value) {
    // No Setup required
  }

  public ViewAction<T> cloneInstance() {
    return new SupressEventAction<T>(getViewId(), getTriggerType());
  }
}
