package com.sarality.app.view.list;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.sarality.app.view.datasource.DataSource;

public class ListComponentLoader<T> extends AsyncTaskLoader<List<T>> {

  private final DataSource<List<T>> datasource;

  public ListComponentLoader(Context context, DataSource<List<T>> datasource) {
    super(context);
    this.datasource = datasource;
  }

  @Override
  public List<T> loadInBackground() {
    return loadData();
  }
  
  public List<T> loadData() {
    List<T> list = new ArrayList<T>(); 
    datasource.loadData();
    list.addAll(datasource.getData());
    return  list;
  }
}
