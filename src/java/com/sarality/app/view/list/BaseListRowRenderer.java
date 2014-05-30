package com.sarality.app.view.list;

import java.util.List;

import android.util.Log;
import android.view.View;

import com.sarality.app.view.action.ClickActionPerformer;
import com.sarality.app.view.action.LongClickActionPerformer;
import com.sarality.app.view.action.TouchActionPerformer;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;

/**
 * Base implementation of {@link ListRowRenderer}.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The Type of data object that is used to render the List row.
 */
public abstract class BaseListRowRenderer<T> implements ListRowRenderer<T> {

  private static final String TAG = "BaseListRowRenderer";

  @Override
  public void setupActions(View rowView, T value, List<ViewAction> actionList) {
    for (ViewAction action : actionList) {
      if (Log.isLoggable(TAG, Log.VERBOSE)) {
        Log.v(TAG, "Setting up Listener for for " + action.getTriggerType() + " Event on view with Id " + action.getViewId() 
            + " inside row with Id " + rowView.getId());
      }
      View view = rowView.findViewById(action.getViewId());
      if (view == null) {
        String message = "Invalid Configuration for " + action.getTriggerType() + " Event. No View with Id " + action.getViewId() 
            + " found in row " + rowView.getId();
        IllegalStateException exception = new IllegalStateException(message);
        Log.e(TAG, message, exception);
        throw exception;
      }
      TriggerType input = action.getTriggerType();
      if (input == TriggerType.CLICK) {
        new ClickActionPerformer(action).setupListener(view);
      } else if (input == TriggerType.LONG_CLICK) {
        new LongClickActionPerformer(action).setupListener(view);
      } else if (input == TriggerType.TOUCH || input == TriggerType.TOUCH_DOWN || input == TriggerType.TOUCH_UP) {
        new TouchActionPerformer(action).setupListener(view);;
      }
    }
  }
}
