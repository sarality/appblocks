package com.sarality.app.view.list;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.view.datasource.DataSource;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class ListComponentLoader<T> extends AsyncTaskLoader<List<T>> {

  private static final String TAG = "ListComponentLoader";
  private final DataSource<T> datasource;

  public ListComponentLoader(Context context, DataSource<T> datasource) {
    super(context);
    this.datasource = datasource;
  }

  @Override
  public List<T> loadInBackground() {
    return loadData();
  }
  
  public List<T> loadData() {
    Log.d(TAG,"Loading background");
    List<T> list = new ArrayList<T>(); 
    datasource.loadData();
    list.addAll(datasource.getData());
    return  list;
  }
}
