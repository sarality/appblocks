package com.sarality.app.view.list;

/**
 * Assists in filtering the the listview with query string
 *
 * @author sunayna@dothat.in sunayna
 */
public class FilterQueryUpdater<T> {
  private final FilteredListViewAdapter<T> adapter;

  FilterQueryUpdater(FilteredListViewAdapter<T> adapter) {
    this.adapter = adapter;
  }

  public void setFilterQuery(String value) {
    adapter.getFilter().filter(value);
  }
}
