package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.sarality.app.view.action.ComponentActionManager;

import java.util.List;

/**
 * Component Object that searches through the list and updates listview
 *
 * @param <T> List Data
 * @author sunayna@dothat.in sunayna
 */
public class SearchListComponent<T> extends ListComponent<T> {

  private final FragmentActivity activity;
  private final ListRowRenderer<T> rowRenderer;
  private final ListItemFilter<T> listItemFilter;
  private SearchListComponentAdapter<T> adapter;

  /**
   * Constructor
   *
   * @param activity - FragmentActivity on which the listView is created
   * @param view - the listview to be populated and rendered
   * @param rowRenderer - custom renderer as specified by the activity
   * @param listItemFilter - Filter criteria
   */
  public SearchListComponent(FragmentActivity activity, ListView view, ListRowRenderer<T> rowRenderer,
                             ListItemFilter<T> listItemFilter) {
    super(activity, view, rowRenderer);
    this.activity = activity;
    this.rowRenderer = rowRenderer;
    this.listItemFilter = listItemFilter;
  }

  @Override
  protected ListComponentAdapter<T> createAdapter(List<T> data) {
    ComponentActionManager componentManager = new ComponentActionManager(getRowActions());
    adapter = new SearchListComponentAdapter<T>(activity, rowRenderer, data, componentManager, listItemFilter);
    return adapter;
  }

  public SearchListComponentAdapter<T> getAdapter() {
    return adapter;
  }
}
