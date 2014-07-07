package com.sarality.app.view.action;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Performs the Action when the given view is clicked.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of data that is used to setup the view on which the action is being performed.
 */
public class ItemClickActionPerformer<T> extends BaseActionPerformer<T> implements OnItemClickListener {

  public ItemClickActionPerformer(ViewAction<T> action) {
    super(action);
  }

  @Override
  public void setupListener(View view) {
    AdapterView<?> adapterview = (AdapterView<?>) view;
    if (isValidListenerView(view)) {
      adapterview.setOnItemClickListener(this);
    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
    getAction().performAction(view, new ViewActionTrigger(view, TriggerType.ITEM_CLICK, null),
        new ViewDetail(view, null));

  }
}
