package com.sarality.app.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.sarality.app.loader.DataConsumer;
import com.sarality.app.loader.DataLoader;
import com.sarality.app.view.datasource.DataSource;
import com.sarality.app.view.list.ListViewAdapter;
import com.sarality.app.view.list.ListViewRowRenderer;

import java.util.List;

/**
 * A Dialog that displays a dynamic list loaded from a data source.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class DynamicListDialogFragment<T> extends BaseDialogFragment implements DataConsumer<List<T>> {

  private final DataSource<List<T>> dataSource;
  private final ListViewAdapter<T> adapter;

  public DynamicListDialogFragment(FragmentActivity activity, ListViewRowRenderer<T> renderer,
      DataSource<List<T>> dataSource) {
    super(activity);
    this.dataSource = dataSource;
    this.adapter = new ListViewAdapter<T>(activity, dataSource.getData(), renderer);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    // Start loading data here.
    getFragmentActivity().getSupportLoaderManager()
        .initLoader(0, null, new DataLoader<List<T>>(getActivity(), dataSource, this));
  }

  @Override
  public void consume(List<T> dataList) {
    adapter.reinitialize(dataList);
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    configureAlertDialogBuilder(builder);

    // TODO(abhideep): Add support for ClickAction here.
    builder.setAdapter(adapter, null);

    Dialog dialog = builder.create();
    configure(dialog);
    return dialog;
  }
}
