package com.sarality.app.view.action;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Performs the Action when the view for an Item in a ListView is clicked.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class ClickListViewItemActionPerformer extends BaseViewActionPerformer
    implements AdapterView.OnItemClickListener {
  
  /**
   * Constructor.
   * 
   * @param action Action that needs to be performed on ListView item click.
   */  
  public ClickListViewItemActionPerformer(ViewAction action) {
    super(action);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
    getAction().performAction(view, new ViewActionTrigger(view, TriggerType.CLICK, null),
        new ListViewRowDetail(view, parent, position, rowId));
  }

  @Override
  public void setupListener(View view) {
    if (isValidListenerView(view)) {
      ListView listView = (ListView) view;
      listView.setOnItemClickListener(this);
    }
  }
}
