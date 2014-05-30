package com.sarality.app.view.action;

import android.view.View;
import android.view.View.OnLongClickListener;

/**
 * Performs the Action when the given view is long clicked.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class LongClickActionPerformer extends BaseActionPerformer implements OnLongClickListener {

  /**
   * Constructor.
   * 
   * @param action Action that needs to be performed on a long click.
   */
  public LongClickActionPerformer(ViewAction action) {
    super(action);
  }

  @Override
  public boolean onLongClick(View view) {
    return getAction().performAction(view, new ViewActionTrigger(view, TriggerType.LONG_CLICK, null),
        new ViewDetail(view, null));
    
  }
  
  @Override
  public void setupListener(View view) {
    if (isValidListenerView(view)) {
      view.setOnLongClickListener(this);
    }
  }

}
