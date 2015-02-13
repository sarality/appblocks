package com.sarality.app.view.list;

import android.view.View;
import android.widget.AdapterView;

import com.sarality.app.view.action.BaseAction;

/**
 * Base implementation of an Action on a ListView.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class ListViewAction extends BaseAction<ListViewAction> implements AdapterView.OnItemClickListener,
    AdapterView.OnItemLongClickListener, AdapterView.OnItemSelectedListener {

  public static final int POSITION_UNDEFINED = -1;
  public static final int ROW_ID_UNDEFINED = -1;

  private View view;
  private View listView;
  private AdapterView<?> adapterView;
  private int listPosition = POSITION_UNDEFINED;
  private long rowId = ROW_ID_UNDEFINED;

  protected void setContext(View view, View listView, AdapterView<?> parent,  int position, long id) {
    setView(view);
    setListView(listView);
    setAdapterView(parent);
    setListPosition(position);
    setRowId(id);
  }

  @Override
  public final void setContext(ListViewAction action) {
    setContext(action.getView(), action.getListView(), action.getAdapterView(), action.getListPosition(),
        action.getRowId());
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    setContext(view, parent, parent, position, id);
    perform();
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
    setContext(view, parent, parent, position, id);
    return perform();
  }

  @Override
  public final void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    setContext(view, parent, parent, position, id);
    perform();
  }

  @Override
  public final void onNothingSelected(AdapterView<?> parent) {
    this.listView = parent;
    perform();
  }

  @Override
  protected final ListViewAction getInstance() {
    return this;
  }

  protected final View getView() {
    return view;
  }

  protected final void setView(View view) {
    this.view = view;
  }

  protected final View getListView() {
    return listView;
  }

  protected final void setListView(View listView) {
    this.listView = listView;
  }

  protected final AdapterView<?> getAdapterView() {
    return adapterView;
  }

  protected final void setAdapterView(AdapterView<?> adapterView) {
    this.adapterView = adapterView;
  }

  protected final int getListPosition() {
    return listPosition;
  }

  protected final void setListPosition(int listPosition) {
    this.listPosition = listPosition;
  }

  protected final long getRowId() {
    return rowId;
  }

  protected final void setRowId(long rowId) {
    this.rowId = rowId;
  }
}
