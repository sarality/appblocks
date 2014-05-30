package com.sarality.app.view.list;


import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListComponent<T> {
  private static final String TAG = "ListComponent";
  
  private final ListView view;
  private final ListComponentLoader<T> mLoader;
  
  public ListComponent(ListView view, 
      ListComponentLoader<T> mLoader) {
    this.view = view;
    this.mLoader = mLoader;
  }

  public ListView getView() {
    return view;
  }

  public void setAdapter(ArrayAdapter<T> adapter) {
    Log.d(TAG, "setting adapter");
    view.setAdapter(adapter);
  }

  public void refresh(){
    mLoader.onContentChanged();
  }
  
}
