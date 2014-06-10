package com.sarality.app.view.list;

import java.util.List;

import android.util.Log;
import android.view.View;

import com.sarality.app.view.action.LongClickActionPerformer;
import com.sarality.app.view.action.MultiClickActionPerformer;
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
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value, List<ViewAction<T>> actionList) {
    MultiClickActionPerformer<T> multiClicks = new MultiClickActionPerformer<T>();
    for (ViewAction<T> action : actionList) {
      ViewAction<T> clonedAction;
      View view = rowView.findViewById(action.getViewId());
      if (view == null) {
        String message = "Invalid Configuration for " + action.getTriggerType() + " Event. No View with Id " + action.getViewId() 
            + " found in row " + rowView.getId();
        IllegalStateException exception = new IllegalStateException(message);
        Log.e(TAG, message, exception);
        throw exception;
      }
      
      clonedAction = action.cloneInstance();
      if (clonedAction == null) {
        Log.e(TAG, "Action for Trigger type" + action.getTriggerType() + "could not be cloned, Using original " +
            "instance. Actions may not perform as desired");
        clonedAction = action;
      }

      clonedAction.prepareView(view, value);
      TriggerType input = action.getTriggerType();
      if (input == TriggerType.CLICK) {
        multiClicks.setupListener(view, clonedAction);
      } else if (input == TriggerType.LONG_CLICK) {
        new LongClickActionPerformer<T>(clonedAction).setupListener(view);
      } else if (input == TriggerType.TOUCH || input == TriggerType.TOUCH_DOWN || input == TriggerType.TOUCH_UP) {
        new TouchActionPerformer<T>(clonedAction).setupListener(view);;
      }
    }
  }
}
