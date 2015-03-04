package com.sarality.app.view.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * A ListDataSource with static list of elements
 *
 * @param <T> Type of data provided by the data set.
 * @author sunayna@dothat.in (Sunayna Uberoy)
 */
public class StaticListDataSource<T> implements DataSource<List<T>> {
  private final List<T> dataset = new ArrayList<T>();

  public StaticListDataSource(T... elements) {
    for (T element : elements) {
      dataset.add(element);
    }
  }

  public StaticListDataSource(List<T> elementList) {
    dataset.addAll(elementList);
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
  public List<T> getData() {
    return dataset;
  }
}
