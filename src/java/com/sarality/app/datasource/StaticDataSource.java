package com.sarality.app.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * A ListDataSource with static list of elements
 * 
 * @author abhideep@dothat.in (Abhideep Singh)
 *
 * @param <T> Type of data provided by the data set.
 */
public class StaticDataSource<T> implements DataSource<T> {
  private final List<T> dataset = new ArrayList<T>();

  public StaticDataSource(T... elements) {
    for (T element : elements) {
      dataset.add(element);
    }
  }

  public StaticDataSource(List<T> elementList) {
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
    // TODO(abhideep): See if we need to clone this
    return dataset;
  }
}
