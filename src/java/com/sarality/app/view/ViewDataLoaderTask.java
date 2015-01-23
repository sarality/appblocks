package com.sarality.app.view;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.sarality.app.view.datasource.DataSource;

/**
 * Asynchronous loader that loads the data asynchronously.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewDataLoaderTask<T> extends AsyncTaskLoader<T> {

  private final DataSource<T> dataSource;

  public ViewDataLoaderTask(Context context, DataSource<T> dataSource) {
    super(context);
    this.dataSource = dataSource;
  }

  @Override
  public T loadInBackground() {
    dataSource.loadData();
    return dataSource.getData();
  }
}
