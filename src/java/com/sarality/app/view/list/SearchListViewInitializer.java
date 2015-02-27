package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import java.util.List;

/**
 * Component Object that searches through the list and updates listview
 *
 * @param <T> List Data
 * @author sunayna@dothat.in sunayna
 */
public class SearchListViewInitializer<T> extends ListViewInitializer<T> {

  private final FragmentActivity activity;
  private final ListViewRowRenderer<T> rowRenderer;
  private final ListItemFilter<T> listItemFilter;
  private SearchListViewAdapter<T> adapter;

  /**
   * Constructor
   *
   * @param activity - FragmentActivity on which the listView is created
   * @param view - the listview to be populated and rendered
   * @param rowRenderer - custom renderer as specified by the activity
   * @param listItemFilter - Filter criteria
   */
  public SearchListViewInitializer(FragmentActivity activity, ListView view, ListViewRowRenderer<T> rowRenderer,
                                   ListItemFilter<T> listItemFilter) {
    super(activity, view, rowRenderer);
    this.activity = activity;
    this.rowRenderer = rowRenderer;
    this.listItemFilter = listItemFilter;
  }

  @Override
  protected ListViewAdapter<T> createAdapter(List<T> data) {
    adapter = new SearchListViewAdapter<T>(activity, rowRenderer, data, listItemFilter);
    return adapter;
  }

  public SearchListViewAdapter<T> getAdapter() {
    return adapter;
  }
}
