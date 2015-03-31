package com.sarality.app.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.sarality.app.view.datasource.DataSource;

/**
 * Creates the loader for asynchronous loading of data and then processed the data when it is loaded.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DataLoader<T> implements LoaderManager.LoaderCallbacks<T> {

  private final Context context;
  private final DataSource<T> dataSource;
  private final DataConsumer<T> consumer;

  public DataLoader(Context context, DataSource<T> dataSource, DataConsumer<T> consumer) {
    this.context = context;
    this.dataSource = dataSource;
    this.consumer = consumer;
  }

  @Override
  public Loader<T> onCreateLoader(int id, Bundle args) {
    return new DataLoaderTask<T>(context, dataSource);
  }

  @Override
  public void onLoadFinished(Loader<T> loader, T data) {
    consumer.consume(data);
  }

  @Override
  public void onLoaderReset(Loader<T> loader) {
    // Do nothing
  }
}
