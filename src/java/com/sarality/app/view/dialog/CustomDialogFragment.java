package com.sarality.app.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarality.app.loader.DataConsumer;
import com.sarality.app.loader.DataLoader;
import com.sarality.app.view.ViewRenderer;
import com.sarality.app.view.datasource.DataSource;

/**
 * A DialogFragment that render a custom view inside a Dialog.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class CustomDialogFragment<V extends View, T> extends BaseDialogFragment implements DataConsumer<T> {

  private final int viewResourceId;
  private final ViewRenderer<V, T> renderer;
  private final DataSource<T> dataSource;

  public CustomDialogFragment(FragmentActivity activity, int viewResourceId, ViewRenderer<V, T> renderer,
      DataSource<T> dataSource) {
    super(activity);
    this.viewResourceId = viewResourceId;
    this.renderer = renderer;
    this.dataSource = dataSource;
  }

  @Override
  @SuppressWarnings("unchecked")
  public V getView() {
    return (V) super.getView();
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

  @Override
  protected final AlertDialog.Builder configureAlertDialogBuilder(AlertDialog.Builder builder) {
    // The Builder is not used for Custom Dialog Fragments
    return null;
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    configure(dialog);
    return dialog;
  }
}
