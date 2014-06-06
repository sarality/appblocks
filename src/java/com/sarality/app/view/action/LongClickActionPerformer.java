package com.sarality.app.view.action;

import android.view.View;
import android.view.View.OnLongClickListener;

/**
 * Performs the Action when the given view is long clicked.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of data that is used to setup the view on which the long click action is being performed.
 */
public class LongClickActionPerformer<T> extends BaseActionPerformer<T> implements OnLongClickListener {

  /**
   * Constructor.
   * 
   * @param action Action that needs to be performed on a long click.
   */
  public LongClickActionPerformer(ViewAction<T> action) {
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
