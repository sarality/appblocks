package com.sarality.app.view.action;

import android.widget.AdapterView;


public class ListViewRowDetail extends ViewDetail {
  private final int position;
  private final long rowId;
  
  public ListViewRowDetail(AdapterView<?> parent, int position, long rowId) {
    super(parent);
    this.position = position;
    this.rowId = rowId;
  }

  public int getPosition() {
    return position;
  }

  public long getRowId() {
    return rowId;
  }
  
  
}
