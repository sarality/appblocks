package com.sarality.app.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarality.app.loader.DataConsumer;
import com.sarality.app.loader.DataLoader;
import com.sarality.app.view.ViewRenderer;
import com.sarality.app.view.datasource.DataSource;

/**
 * A DialogFragment that render a custom view inside a Dialog.
 * <p/>
 * TODO(abhideep): Add Action support.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class CustomViewDialogFragment<V extends View, T> extends DialogFragment implements DataConsumer<T> {

  private DataSource<T> dataSource;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the view identified by the resource id as the view for the fragment.
    return inflater.inflate(getLayoutResourceId(), null, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    dataSource = createDataSource();
    if (dataSource == null) {
      render(null);
    } else {
      startLoad(dataSource, getLoaderId(), this);
    }
  }

  /**
   * @return Resource Id for the View used to render the Fragment.
   */
  protected abstract int getLayoutResourceId();

  /**
   * @return View Id of the View that is rendered by the Renderer. Return {@code null} when a renderer is not defined.
   */
  protected abstract int getRendererViewId();

  protected V getRendererView() {
    @SuppressWarnings("unchecked")
    V view = (V) getView().findViewById(getRendererViewId());
    return view;
  }

  /**
   * Creates a data source used to load the data for the fragment.
   *
   * @return The DataSource used to load data for the fragment.
   */
  protected abstract DataSource<T> createDataSource();

  /**
   * Returns a unique Id to be used for the loader that loads the data from the data source.
   * <p/>
   * The fragment MUST override this method if there are more than one loader in the activity. Returns 0 by default
   * with the assumption that there is only one loader being used in the activity.
   *
   * @return Unique Id for the loader.
   */
  protected int getLoaderId() {
    return 0;
  }

  /**
   * Re-initializes the Fragment.
   */
  protected void reinitialize() {
    startLoad(dataSource, getLoaderId(), this);
  }

  /**
   * Start a Loader with the given data source using the given loaderId.
   *
   * @param dataSource DataSource to load the data from
   * @param loaderId Id to used for the loader
   */
  protected <D> void startLoad(DataSource<D> dataSource, int loaderId, DataConsumer<D> consumer) {
    // Kill all previously loaded data by the activity
    getActivity().getSupportLoaderManager()
        .initLoader(loaderId, null, new DataLoader<D>(getActivity(), dataSource, consumer)).forceLoad();
  }

  @Override
  public void consume(T data) {
    render(data);
  }

  /**
   * @return Renderer to be used to render the
   */
  protected abstract ViewRenderer<V, T> getRenderer();

  /**
   * Renders the Fragment View.
   * <p/>
   * By default, it calls the Renderer defined by the Fragment, but the Fragment can override this method to perform
   * additional rendering logic.
   *
   * @param data Data to render the view.
   */
  protected void render(T data) {
    ViewRenderer<V, T> renderer = getRenderer();
    if (renderer != null) {
      renderer.render(getRendererView(), data);
    }
  }

  protected abstract void configure(Dialog dialog);

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    configure(dialog);
    return dialog;
  }
}
