package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;

import com.sarality.app.view.BaseViewInitializer;
import com.sarality.app.view.ViewRenderer;

import java.util.List;

/**
 * Initializes a simple ListView using the given Row Renderer.
 *
 * @param <T> Type of data needed to render a row in the ListView.
 * @author abhideep@ (Abhideep Singh)
 */
public class ListViewInitializer<T> extends BaseViewInitializer<ListView, List<T>> {

  private final ListViewRowRenderer<T> rowRenderer;
  private ListItemFilter<T> filter;
  private EmptyListViewRenderer<?> emptyListViewRenderer;

  public ListViewInitializer(FragmentActivity activity, ListView view, ListViewRowRenderer<T> rowRenderer) {
    super(activity, view);
    this.rowRenderer = rowRenderer;
  }

  public <D> ListViewInitializer<T> withEmptyListView(View emptyView, ViewRenderer<View, D> emptyViewRenderer, D data) {
    this.emptyListViewRenderer = new EmptyListViewRenderer<D>(emptyView, emptyViewRenderer, data);
    return this;
  }

  @Override
  public void render(List<T> dataList) {
    if (emptyListViewRenderer != null) {
      emptyListViewRenderer.render(getView());
    }
    getView().setAdapter(createAdapter(dataList));
    setupActions();
  }

  public ListViewInitializer<T> withFilter(ListItemFilter<T> filter) {
    this.filter = filter;
    return this;
  }

  @SuppressWarnings("unchecked")
  protected ListViewAdapter<T> createAdapter(List<T> dataList) {
    if (filter != null) {
      return new FilteredListViewAdapter<T>(getContext(), dataList, rowRenderer, filter);
    } else {
      return new ListViewAdapter<T>(getContext(), dataList, rowRenderer);
    }
  }

}
