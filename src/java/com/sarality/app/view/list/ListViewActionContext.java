package com.sarality.app.view.list;

import android.view.View;
import android.widget.AdapterView;

import com.sarality.app.view.action.AppViewActionContext;

/**
 * The context needed to execute an Action on a ListView.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListViewActionContext extends AppViewActionContext {

  private final View listView;
  private final AdapterView<?> adapterView;
  private final int position;
  private final long rowId;

  public ListViewActionContext(View view, View listView, int position, long rowId) {
    super(view);
    this.listView = listView;
    this.adapterView = null;
    this.position = position;
    this.rowId = rowId;
  }

  public ListViewActionContext(View view, AdapterView<?> adapterView, int position, long rowId) {
    super(view);
    this.listView = adapterView;
    this.adapterView = adapterView;
    this.position = position;
    this.rowId = rowId;
  }

  public View getListView() {
    return listView;
  }

  public AdapterView<?> getAdapterView() {
    return adapterView;
  }

  public int getPosition() {
    return position;
  }

  public long getRowId() {
    return rowId;
  }

}
