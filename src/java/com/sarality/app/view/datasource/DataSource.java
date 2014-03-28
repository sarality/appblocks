package com.sarality.app.view.datasource;

import java.util.List;

/**
 * Interface for all classes that provide data a Component
 * 
 * @author abhideep@dothat.in (Abhideep Singh)
 */
public interface DataSource<T> {

  /**
   * @return true if the data set is of a fixed size.
   */
  public boolean isStaticDataSet();

  /**
   * Loads the data that needs to be displayed
   */
  public void loadData();

  /**
   * @return List of data that needs to be displayed
   */
  public List<T> getData();
}
