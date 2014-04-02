package com.sarality.app.view.list;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.sarality.app.view.datasource.DataSource;

public class ListComponent<T> {
  private static final String TAG = "ListComponent";
  
  private final Activity activity;
  private final ListView view;
  private final DataSource<T> datasource;
  private final ListRowRenderer<T> rowRenderer;
  
  public ListComponent(Activity activity, ListView view, ListRowRenderer<T> rowRenderer,
      DataSource<T> datasource) {
    this.activity = activity;
    this.view = view;
    this.datasource = datasource;
    this.rowRenderer = rowRenderer;
  }

  public ListView getView() {
    return view;
  }

  public DataSource<T> getDataSource() {
    return datasource;
  }

  public void init() {
    //TODO(abhideep): Add Async support here
    datasource.loadData();

    // TODO(abhideep): Treat static and dynamic data sources differently
    List<T> dataList = datasource.getData();
    Log.d(TAG, "List Component will display " + dataList.size() + " items");
    view.setAdapter(new ListComponentAdapter<T>(activity, rowRenderer, datasource.getData()));
  }
}
