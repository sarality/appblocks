package com.sarality.app.view.action;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ClickActionPerformer implements OnClickListener {

  private final String TAG = "ClickActionPerformer";

  private final ViewAction action;

  public ClickActionPerformer(ViewAction action) {
    this.action = action;
  }

  @Override
  public void onClick(View view) {
    Log.d(TAG, "Processing Click event for view with Id " + view.getId() + " and view " + view);
    action.performAction(view, new ViewActionDetail(null), new ViewDetail(null));
  }

  public void setupListener(View view) {
    view.setOnClickListener(this);
  }
}
