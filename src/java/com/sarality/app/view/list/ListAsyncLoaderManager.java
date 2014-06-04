package com.sarality.app.view.list;

import java.util.List;

import com.sarality.app.view.datasource.DataSource;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

/**
 * Async Loader that will load the data from the database in the background and not on the 
 * UI thread
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ListAsyncLoaderManager<T> implements LoaderManager.LoaderCallbacks<List<T>>{

  private static final String TAG = "ListAsyncLoaderManager";
  private final DataSource<T> dataSource;
  private final Activity context;
  private ListComponentLoader<T> loader;
  private final ListRowRenderer<T> renderer;
  private ListComponentAdapter<T> adapter;
  private final ListComponent<T> component;
  
  public ListAsyncLoaderManager(Activity context, DataSource<T> dataSource,
      BaseListRowRenderer<T> renderer, ListComponent<T> component){
    this.context = context;
    this.dataSource = dataSource;
    this.renderer = renderer;
    this.component = component;
  }
  
  @Override
  public Loader<List<T>> onCreateLoader(int id, Bundle args) {
    Log.d(TAG, "LoaderCreated");
    loader = new ListComponentLoader<T>(context, dataSource);
    return loader;
  }

  @Override
  public void onLoadFinished(Loader<List<T>> loader, List<T> data) {
    adapter = new ListComponentAdapter<T>(context, renderer, data,
        component.getRowActions());
    component.setAdapter(adapter);
  }

  @Override
  public void onLoaderReset(Loader<List<T>> arg0) {
    // TODO Auto-generated method stub
  }

  public void onContentChanged() {
    loader.onContentChanged();
  }
}
