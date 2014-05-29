package com.sarality.app.view.action;

import android.view.View;
import android.widget.AdapterView;

public class ListItemLongClickActionPerformer implements AdapterView.OnItemLongClickListener {
  private final ViewAction action;
  
  public ListItemLongClickActionPerformer(ViewAction action) {
    this.action = action;
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long rowId) {
    action.performAction(view, new ViewActionDetail(null), new ListViewRowDetail(parent, position, rowId));
    return true;
  }
}
