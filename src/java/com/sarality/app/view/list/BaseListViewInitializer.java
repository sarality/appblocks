package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.sarality.app.view.BaseViewInitializer;
import com.sarality.app.view.ViewRenderer;

import java.util.List;

/**
 * Implementation of Initializer that is common to all Initializer of List View Initializers.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseListViewInitializer<V extends View, T> extends BaseViewInitializer<V, List<T>> {

  private final ListViewRowRenderer<T> rowRenderer;
  private EmptyListViewRenderer<?> emptyListViewRenderer;

  public BaseListViewInitializer(FragmentActivity activity, V view, ListViewRowRenderer<T> rowRenderer, int loaderId) {
    super(activity, view, new ListViewActionManager(activity), loaderId);
    this.rowRenderer = rowRenderer;
  }

  protected EmptyListViewRenderer<?> getEmptyListViewRenderer() {
    return emptyListViewRenderer;
  }

  public <D> void setEmptyListView(View emptyView, ViewRenderer<View, D> emptyViewRenderer, D data) {
    this.emptyListViewRenderer = new EmptyListViewRenderer<D>(emptyView, emptyViewRenderer, data);
  }

  /**
   * @return Renderer to be used to render eah row in the List.
   */
  protected ListViewRowRenderer<T> getRowRenderer() {
    return rowRenderer;
  }

  /**
   * @return ListViewActionManager for the Initializer.
   */
  public ListViewActionManager getActionManager() {
    return (ListViewActionManager) super.getActionManager();
  }

}
