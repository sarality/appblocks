package com.sarality.app.view.list;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.datasource.DataSource;

public class ListComponent<T> {
  private static final String TAG = "ListComponent";
  
  private final Activity activity;
  private final ListView view;
  private final ListRowRenderer<T> rowRenderer;
  private List<ViewAction> actionList = Lists.of();
  private List<ViewAction> rowActionList = Lists.of();

  private DataSource<T> dataSource;
  private ListComponentAdapter<T> adapter;
  
  public ListComponent(Activity activity, ListView view, ListRowRenderer<T> rowRenderer) {
    this.activity = activity;
    this.view = view;
    this.rowRenderer = rowRenderer;
  }

  public ListView getView() {
    return view;
  }

  public void registerRowAction(ViewAction action) {
    rowActionList.add(action);
  }

  public void registerAction(ViewAction action) {
    actionList.add(action);
  }
  
  public List<ViewAction> getRowActions() {
    return rowActionList;
  }

  //TODO(abhideep): Add Async support here
  public void init(DataSource<T> source) {
    dataSource = source;
    dataSource.loadData();

    List<T> dataList = dataSource.getData();
    adapter = new ListComponentAdapter<T>(activity, rowRenderer, dataList, rowActionList);
    Log.v(TAG, "List Component will display " + dataList.size() + " items");

    // TODO(abhideep): Treat static and dynamic data sources differently
    view.setAdapter(adapter);
  }
}
