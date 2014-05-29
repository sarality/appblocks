package com.sarality.app.view.action;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListItemClickActionPerformer implements AdapterView.OnItemClickListener {
  private final ViewAction action;
  
  public ListItemClickActionPerformer(ViewAction action) {
    this.action = action;
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
    action.performAction(view, new ViewActionDetail(null), new ListViewRowDetail(parent, position, rowId));
  }

  public void setupListener(ListView view) {
    view.setOnItemClickListener(this);
  }
}
