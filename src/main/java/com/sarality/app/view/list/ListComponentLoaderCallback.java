package com.sarality.app.view.list;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.sarality.app.view.datasource.DataSource;

/**
 * Async Loader that will load the data from the database in the background and not on the 
 * UI thread
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ListComponentLoaderCallback<T> implements LoaderManager.LoaderCallbacks<List<T>>{

  private final DataSource<List<T>> dataSource;
  private final Activity context;
  private ListComponentLoader<T> loader;
  private final ListComponent<T> component;
  
  public ListComponentLoaderCallback(Activity context, DataSource<List<T>> dataSource, ListComponent<T> component){
    this.context = context;
    this.dataSource = dataSource;
    this.component = component;
  }
  
  @Override
  public Loader<List<T>> onCreateLoader(int id, Bundle args) {
    loader = new ListComponentLoader<T>(context, dataSource);
    return loader;
  }

  @Override
  public void onLoadFinished(Loader<List<T>> loader, List<T> data) {
    component.render(data);
  }

  @Override
  public void onLoaderReset(Loader<List<T>> arg0) {
    // TODO Auto-generated method stub
  }

  public void onContentChanged() {
    loader.onContentChanged();
  }
}
