package com.sarality.app.view.list;

import java.util.List;

import com.sarality.app.view.action.ViewAction;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * An ArrayAdapter for a List Component.
 * <p>
 * This is implementation detail that is hidden from the caller.
 * 
 * @author abhideep@dothat.in (Abhideep Singh)
 *
 * @param <T> The data/model for each row in the list
 */
class ListComponentAdapter<T> extends ArrayAdapter<T> {
  private static final String TAG = "ListComponentAdapter";
  private final Activity context;
  private final ListRowRenderer<T> rowRenderer;
  private List<T> rowValueList;
  private final List<ViewAction> actionList;

  ListComponentAdapter(Activity context, ListRowRenderer<T> rowRenderer, List<T> rowValueList, 
      List<ViewAction> actionList) {
    super(context, 0, rowValueList);
    this.context = context;
    this.rowRenderer = rowRenderer;
    this.rowValueList = rowValueList;
    this.actionList = actionList;
  }

  public void reinitalize(List<T> data){
     this.rowValueList = data;
     notifyDataSetChanged();
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View rowView = convertView;
    T rowValue = rowValueList.get(position);
    if (rowView == null) {
      // Inflate a new row into the list
      LayoutInflater inflater = context.getLayoutInflater();
      rowView = inflater.inflate(rowRenderer.getRowLayout(rowValue), null);

      // Cache the various views for the row      
      ListRowViewCache viewCache = new ListRowViewCache();
      rowRenderer.populateViewCache(rowView, viewCache, rowValue);
      rowView.setTag(viewCache);
    }

    ListRowViewCache viewCache = (ListRowViewCache) rowView.getTag();
    rowRenderer.render(rowView, viewCache, rowValue);

    // Setup actions on the new row
    rowRenderer.setupActions(rowView, rowValue, actionList);
    return rowView;
  }

  @Override
  public int getCount() {
    Log.d(TAG, "Count is " + rowValueList.size());
    return rowValueList.size();
  }
}
