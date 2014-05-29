package com.sarality.app.view.list;

import java.util.List;

import android.util.Log;
import android.view.View;

import com.sarality.app.view.action.ClickActionPerformer;
import com.sarality.app.view.action.InputEvent;
import com.sarality.app.view.action.TouchActionPerformer;
import com.sarality.app.view.action.ViewAction;

public abstract class BaseListRowRenderer<T> implements ListRowRenderer<T> {

  private static final String TAG = "BaseListRowRenderer";

  @Override
  public void setupActions(View rowView, T value, List<ViewAction> actionList) {
    for (ViewAction action : actionList) {
      Log.d(TAG, "Setting up Listener for for Action " + action.getEvent() + " on view with Id " + action.getViewId() 
          + " inside row with Id " + rowView.getId());
      View view = rowView.findViewById(action.getViewId());
      if (view == null) {
        Log.e(TAG, "Invalid Configuration. No View with Id " + action.getViewId() + " found in row " + rowView.getId());
      }
      InputEvent input = action.getEvent();
      if (input == InputEvent.CLICK) {
        new ClickActionPerformer(action).setupListener(view);
      } else if (input == InputEvent.LONG_CLICK) {
        new ClickActionPerformer(action).setupListener(view);
      } else if (input == InputEvent.TOUCH) {
        new TouchActionPerformer(action).setupListener(view);;
      }
    }
  }
}
