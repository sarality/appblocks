package com.sarality.app.view.list;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.view.datasource.DataSource;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class ListComponentLoader<T> extends AsyncTaskLoader<List<T>> {

  private static final String TAG = "ListComponentLoader";
  DataSource<T> datasource;

  public ListComponentLoader(Context context, DataSource<T> datasource) {
    super(context);
    this.datasource = datasource;
    
    // TODO Auto-generated constructor stub
  }

  @Override
  public List<T> loadInBackground() {
    Log.d(TAG,"Loading background");
    List<T> list = new ArrayList<T>(); 
    datasource.loadData();
    list.addAll(datasource.getData());
    return  list;
  }

  /*
    private static final String TAG = "ListComponentLoader";
    ListComponent<T> list;
    Activity context;
    private final DataSource<T> datasource;
    private final ListRowRenderer<T> rowRenderer;
    private ListComponentAdapter<T> adapter;
    
    public ListComponentLoader(Activity context,  ListComponent<T> list, 
        ListRowRenderer<T> rowRenderer, DataSource<T> datasource) {
        super(context);
        this.list = list;
        this.context = context;
        this.datasource = datasource;
        this.rowRenderer = rowRenderer;


   }

    @Override
    public ArrayAdapter<T> loadInBackground() {
       Log.d(TAG, "Loading in background"); 
       datasource.loadData();
       adapter = new ListComponentAdapter<T>(context, rowRenderer, datasource.getData());
       return adapter;
    }
    
    @Override
    public void onContentChanged(){
      Log.d(TAG, "Loading on Content changed");
      datasource.loadData();
      adapter.setRowValueList(datasource.getData());
    }*/
}
