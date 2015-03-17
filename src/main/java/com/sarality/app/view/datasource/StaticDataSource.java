package com.sarality.app.view.datasource;

/**
 * A dataSource with a data element
 *
 * @param <T> Type of data provided by the data set.
 * @author sunayna@dothat.in (Sunayna Uberoy)
 */
public class StaticDataSource<T> implements DataSource<T> {
  private final T data;

  public StaticDataSource(T data) {
    this.data = data;
  }

  @Override
  public boolean isStaticDataSet() {
    return true;
  }

  @Override
  public void loadData() {
    // Do nothing
  }

  @Override
  public T getData() {
    return data;
  }
}

