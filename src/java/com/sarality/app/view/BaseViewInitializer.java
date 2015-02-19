package com.sarality.app.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.sarality.app.loader.DataConsumer;
import com.sarality.app.loader.DataLoader;
import com.sarality.app.view.action.ViewActionManager;
import com.sarality.app.view.datasource.DataSource;

/**
 * Base implementations for all classes that initialize Views.
 * <p/>
 * The constructor takes in a {@code FragmentActivity} so that the DataSource can be loaded asynchronously using
 * a {@code Loader}.
 *
 * @param <V> Type of view being initialized.
 * @param <T> Type of data needed to initialize the view.
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseViewInitializer<V extends View, T> implements ViewInitializer<V, T>, DataConsumer<T> {

  private final int loaderId;
  private final FragmentActivity activity;
  private final V view;
  private final ViewActionManager actionManager;

  public BaseViewInitializer(FragmentActivity activity, V view, ViewActionManager actionManager, int loaderId) {
    this.activity = activity;
    this.view = view;
    this.loaderId = loaderId;
    this.actionManager = actionManager;
  }

  @Override
  public V getView() {
    return view;
  }

  @Override
  public void consume(T data) {
    init(data);
  }

  @Override
  public void init(T data) {
    render(data);
    setupActions();
  }

  @Override
  public void init(DataSource<T> dataSource) {
    activity.getSupportLoaderManager()
        .initLoader(loaderId, null, new DataLoader<T>(activity, dataSource, this)).forceLoad();
  }

  /**
   * Setup actions registered for the view.
   */
  public void setupActions() {
    getActionManager().setup(getView());
  }

  /**
   * @return Context for the Initializer.
   */
  protected Context getContext() {
    return activity;
  }

  /**
   * @return ActionManager being used by the Initializer
   */
  public ViewActionManager getActionManager() {
    return actionManager;
  }
}
