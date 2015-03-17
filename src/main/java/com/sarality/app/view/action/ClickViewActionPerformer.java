package com.sarality.app.view.action;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Performs the Action when the given view is clicked.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of data that is used to setup the view on which the action is being performed.
 */
public class ClickViewActionPerformer extends BaseViewActionPerformer implements OnClickListener {

  public ClickViewActionPerformer(ViewAction action) {
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
