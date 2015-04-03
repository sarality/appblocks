package com.sarality.app.view.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Simple implementation of the Adapter needed to render a ListView.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListViewAdapter<T> extends BaseAdapter {

  private final Context context;
  private final List<T> rowValueList;
  private final ListViewRowRenderer<T> rowRenderer;
  private final boolean measureHeight;

  private int totalHeight = 0;

  public ListViewAdapter(Context context, List<T> rowValueList, ListViewRowRenderer<T> rowRenderer,
      boolean measureHeight) {
    super();
    this.context = context;
    this.rowValueList = rowValueList;
    this.rowRenderer = rowRenderer;
    this.measureHeight = measureHeight;
  }

  public ListViewAdapter(Context context, List<T> rowValueList, ListViewRowRenderer<T> rowRenderer) {
    this(context, rowValueList, rowRenderer, false);
  }

  public void reinitialize(List<T> dataList) {
    this.rowValueList.clear();
    this.rowValueList.addAll(dataList);
    notifyDataSetChanged();
  }

  public ListViewRowRenderer<T> getRowRenderer() {
    return rowRenderer;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View rowView = convertView;
    T rowValue = getItem(position);
    if (rowView == null) {
      LayoutInflater inflater = LayoutInflater.from(context);
      rowView = inflater.inflate(rowRenderer.getRowLayout(position, rowValue), null);
      if (measureHeight) {
        rowView.measure(0, 0);
        totalHeight += rowView.getMeasuredHeight();
      }

      rowRenderer.setupActions(rowView);
    }
    rowRenderer.render(rowView, rowValue);
    return rowView;
  }

  @Override
  public int getViewTypeCount() {
    return rowRenderer.getViewTypeCount();
  }

  @Override
  public int getItemViewType(int position) {
    return rowRenderer.getViewType(position, getItem(position));
  }

  @Override
  public int getCount() {
    return rowValueList.size();
  }

  @Override
  public T getItem(int position) {
    return rowValueList.get(position);
  }

  public int getTotalHeight() {
    return totalHeight;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  protected Context getContext() {
    return context;
  }

}
