package com.sarality.app.view.list;

import android.view.View;

/**
 * The context needed to execute an Action on a ListView.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListViewActionContext {

  private final View view;
  private final View parentView;
  private final int position;
  private final long rowId;

  public ListViewActionContext(View view, View parentView, int position, long rowId) {
    this.view = view;
    this.parentView = parentView;
    this.position = position;
    this.rowId = rowId;
  }

  public View getView() {
    return view;
  }

  public View getParentView() {
    return parentView;
  }

  public int getPosition() {
    return position;
  }

  public long getRowId() {
    return rowId;
  }
}
