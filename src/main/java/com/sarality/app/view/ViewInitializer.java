package com.sarality.app.view;

import android.view.View;

import com.sarality.app.view.datasource.DataSource;

/**
 * Interface for all classes that initialize a view for the given data.
 * <p/>
 * Initialization refers to both the rendering of the view and setting up actions on it.
 *
 * @param <V> Type of View to initialize
 * @param <T> Type of Data needed to render the view.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewInitializer<V extends View, T> {

  /**
   * Returns the view being initialized.
   *
   * @return The View being initialized.
   */
  public V getView();

  /**
   * Setup actions registered for the View.
   */
  public void setupActions();

  /**
   * Render the View for the given data.
   *
   * @param data The data for rendering the view.
   */
  public void render(T data);

  /**
   * Initialize the View for the given data.
   *
   * @param data Data needed to initialize the view.
   */
  public void init(T data);

  /**
   * Initialize the View for the given data source.
   * <p/>
   * Ideally, the data from the data source should be loaded asynchronously and after the data is loaded a call is
   * made to {@link #init(Object)} the view with the loaded data.
   *
   * @param dataSource DataSource to load data from.
   */
  public void init(DataSource<T> dataSource);

}
