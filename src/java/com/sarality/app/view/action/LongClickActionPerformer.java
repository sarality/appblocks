package com.sarality.app.view.action;

import java.util.List;

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
   * @param list Action that needs to be performed on a long click.
   */
  public LongClickActionPerformer(List<ViewAction<T>> list) {
    super(list);
  }

  @Override
  public boolean onLongClick(View view) {
    for(ViewAction<T> action: getActionList()){
      action.performAction(view, new ViewActionTrigger(view, TriggerType.LONG_CLICK, null),
          new ViewDetail(view, null));
    }
    return true;
  }
  
  @Override
  public void setupListener(View view) {
    if (isValidListenerView(view)) {
      view.setOnLongClickListener(this);
    }
  }
}
