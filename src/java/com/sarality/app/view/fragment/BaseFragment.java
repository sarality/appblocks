package com.sarality.app.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarality.app.loader.DataConsumer;
import com.sarality.app.loader.DataLoader;
import com.sarality.app.view.ViewRenderer;
import com.sarality.app.view.datasource.DataSource;

/**
 * A Fragment that loads data from a data source and render a view.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseFragment<V extends View, T> extends Fragment implements DataConsumer<T> {


  private final FragmentActivity activity;
  private final int viewResourceId;
  private final ViewRenderer<V, T> renderer;
  private final DataSource<T> dataSource;

  public BaseFragment(FragmentActivity activity, int viewResourceId, ViewRenderer<V, T> renderer,
      DataSource<T> dataSource) {
    super();
    this.activity =activity;
    this.viewResourceId = viewResourceId;
    this.renderer = renderer;
    this.dataSource = dataSource;
  }

  @Override
  @SuppressWarnings("unchecked")
  public V getView() {
    return (V) super.getView();
  }

  /**
   * @return Fragment activity that instantiated the fragment.
   */
  protected FragmentActivity getFragmentActivity() {
    return activity;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the view identified by the resource id as the view for the fragment.
    return inflater.inflate(viewResourceId, null, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    // Start loading data here. This is called here since the view will already have been created by now.
    if (dataSource != null) {
      getFragmentActivity().getSupportLoaderManager()
          .initLoader(0, null, new DataLoader<T>(getActivity(), dataSource, this));
    }
  }

  @Override
  public void consume(T data) {
    if (renderer != null) {
      renderer.render(getView(), data);
    }
  }
}