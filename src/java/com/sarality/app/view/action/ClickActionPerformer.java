package com.sarality.app.view.action;

import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Performs the Action when the given view is clicked.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of data that is used to setup the view on which the action is being performed.
 */
public class ClickActionPerformer<T> extends BaseActionPerformer<T> implements OnClickListener {
  
  public ClickActionPerformer(List<ViewAction<T>> actionList) {
    super(actionList);
  }


  @Override
  public void onClick(View view) {
    for(ViewAction<T> action: getActionList()){
      action.performAction(view, new ViewActionTrigger(view, TriggerType.CLICK, null),
        new ViewDetail(view, null));
    }
  }
  
  @Override
  public void setupListener(View view) {
     view.setOnClickListener(this);
  }
}
