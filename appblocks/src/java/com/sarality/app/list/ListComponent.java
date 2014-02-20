package com.sarality.app.list;

import android.app.Activity;
import android.widget.ListView;

import com.sarality.app.datasource.DataSource;

public class ListComponent<T> {
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
    if (datasource.isStaticDataSet()) {
      view.setAdapter(new ListComponentAdapter<T>(activity, rowRenderer, datasource.getData()));
    }
  }
}
