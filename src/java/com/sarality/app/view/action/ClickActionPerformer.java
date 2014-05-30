package com.sarality.app.view.action;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Performs the Action when the given view is clicked.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class ClickActionPerformer extends BaseActionPerformer implements OnClickListener {

  public ClickActionPerformer(ViewAction action) {
    super(action);
  }

  @Override
  public void onClick(View view) {
    getAction().performAction(view, new ViewActionTrigger(view, TriggerType.CLICK, null),
        new ViewDetail(view, null));
  }
  
  @Override
  public void setupListener(View view) {
    if (isValidListenerView(view)) {
      view.setOnClickListener(this);
    }
  }
}