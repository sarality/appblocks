package com.sarality.app.view.action;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Performs the Action when the view for an Item in a ListView is long clicked.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The type of data that is used to setup the item view on which the long click action is being performed.
 */
public class ListItemLongClickActionPerformer extends BaseActionPerformer implements AdapterView.OnItemLongClickListener {

  /**
   * Constructor.
   * 
   * @param action Action that needs to be performed on ListRow item long click.
   */  
  public ListItemLongClickActionPerformer(ViewAction action) {
    super(action);
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long rowId) {
    getAction().performAction(view, new ViewActionTrigger(view, TriggerType.LONG_CLICK, null), 
        new ListViewRowDetail(view, parent, position, rowId));
    return true;
  }

  @Override
  public void setupListener(View view) {
    if (isValidListenerView(view)) {
      ListView listView = (ListView) view;
      listView.setOnItemLongClickListener(this);
    }
  }
}
