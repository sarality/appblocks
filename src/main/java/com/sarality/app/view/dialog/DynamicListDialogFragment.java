package com.sarality.app.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

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

  private ListViewAdapter<T> adapter;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    DataSource<List<T>> dataSource = getDataSource();
    startLoad(dataSource, getLoaderId(), this);
    if (dataSource != null) {
      this.adapter = new ListViewAdapter<T>(getActivity(), dataSource.getData(), getListViewRowRenderer());
    } else {
      this.adapter = new ListViewAdapter<T>(getActivity(), null, getListViewRowRenderer());
    }
  }

  /**
   * @return Renderer to be used to render each row of the list displayed in the dialog.
   */
  protected abstract ListViewRowRenderer<T> getListViewRowRenderer();

  /**
   * Returns a unique Id to be used for the loader.
   *
   * @return Unique Id for the loader.
   */
  protected abstract int getLoaderId();

  /**
   * @return The DataSource used to load data for the fragment.
   */
  protected abstract DataSource<List<T>> getDataSource();

  /**
   *
   * @param dataSource DataSource to load the data from
   * @param loaderId Id to used for the loader
   */
  protected <D> void startLoad(DataSource<D> dataSource, int loaderId, DataConsumer<D> consumer) {
    getActivity().getSupportLoaderManager()
        .initLoader(loaderId, null, new DataLoader<D>(getActivity(), dataSource, consumer)).forceLoad();
  }

  @Override
  public void consume(List<T> dataList) {
    render(dataList);
  }


  /**
   * Renders the Fragment View.
   *
   * @param dataList Data to render the view.
   */
  protected void render(List<T> dataList) {
    adapter.reinitialize(dataList);
  }

  protected abstract void configure(Dialog dialog);

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    configure(builder);

    // TODO(abhideep): Add support for ClickAction here.
    builder.setAdapter(adapter, null);

    Dialog dialog = builder.create();
    configure(dialog);
    return dialog;
  }
}
