package com.sarality.app.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

import com.sarality.app.view.datasource.DataSource;

/**
 * Creates the loader for asynchronous loading of data and then processed the data when it is loaded.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewDataLoader<T> implements LoaderManager.LoaderCallbacks<T> {

  private final Context context;
  private final DataSource<T> dataSource;
  private final ViewInitializer<? extends View, T> initializer;

  public ViewDataLoader(Context context, DataSource<T> dataSource, ViewInitializer<? extends View, T> initializer) {
    this.context = context;
    this.dataSource = dataSource;
    this.initializer = initializer;
  }

  @Override
  public Loader<T> onCreateLoader(int id, Bundle args) {
    return new ViewDataLoaderTask<T>(context, dataSource);
  }

  @Override
  public void onLoadFinished(Loader<T> loader, T data) {
    initializer.render(data);
  }

  @Override
  public void onLoaderReset(Loader<T> loader) {
    // Do nothing
  }
}
