package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;

import java.util.List;

/**
 * Initializes a simple ListView using the given Row Renderer.
 *
 * @param <T> Type of data needed to render a row in the ListView.
 * @author abhideep@ (Abhideep Singh)
 */
public class ListViewInitializer<T> extends BaseListViewInitializer<ListView, T> {

  private static final int DEFAULT_LOADER_ID = 0;
  private ListItemFilter<T> filter;

  public ListViewInitializer(FragmentActivity activity, ListView view, ListViewRowRenderer<T> rowRenderer,
      int loaderId) {
    super(activity, view, rowRenderer, loaderId);
  }

  public ListViewInitializer(FragmentActivity activity, ListView view, ListViewRowRenderer<T> rowRenderer) {
    this(activity, view, rowRenderer, DEFAULT_LOADER_ID);
  }

  public void setFilter(ListItemFilter<T> filter) {
    this.filter = filter;
  }

  @Override
  public void render(List<T> dataList) {
    if (getEmptyListViewRenderer() != null) {
      getEmptyListViewRenderer().render(getView());
    }

    // Create a copy of the data so that we don't have a reference to the list in the DataSource. Using the same list
    // becomes a problem if the DataSource is still loading the data and we are rendering the list using the adapter.
    List<T> adapterDataList = Lists.emptyList();
    adapterDataList.addAll(dataList);
    ListViewAdapter<T> adapter = createAdapter(adapterDataList);
    getView().setAdapter(adapter);

    // Notify the ListView, just in case the data is different from the last time render was called.
    adapter.notifyDataSetChanged();

    setupActions();
  }

  @Override
  public void setupActions() {
    super.setupActions();
    getActionManager().setupListView(getView());
  }

  @SuppressWarnings("unchecked")
  protected ListViewAdapter<T> createAdapter(List<T> dataList) {
    if (filter != null) {
      return new FilteredListViewAdapter<T>(getContext(), dataList, getRowRenderer(), filter);
    } else {
      return new ListViewAdapter<T>(getContext(), dataList, getRowRenderer());
    }
  }
}
