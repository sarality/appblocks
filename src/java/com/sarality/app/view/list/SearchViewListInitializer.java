package com.sarality.app.view.list;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;

import com.sarality.app.view.datasource.DataSource;

/**
 * Initializers that has the capability to search within the listview
 *
 * @param <T> Type of data needed to render a row in the ListView.
 * @author sunayna@ (Sunayna Uberoy)
 */
public class SearchViewListInitializer<T> extends ListViewInitializer {
  private final ListView listView;
  private final FragmentActivity activity;
  private final int searchViewId;

  public SearchViewListInitializer(FragmentActivity activity, ListView view, ListViewRowRenderer<T> rowRenderer,
                                   int searchViewId) {
    super(activity, view, rowRenderer);
    this.listView = view;
    this.activity = activity;
    this.searchViewId = searchViewId;
  }

  public SearchViewListInitializer(FragmentActivity activity, ListView view, ListViewRowRenderer<T> rowRenderer,
                                   int loaderId, int searchViewId) {
    super(activity, view, rowRenderer, loaderId);
    this.listView = view;
    this.activity = activity;
    this.searchViewId = searchViewId;
  }

  private void setSearchView() {
    SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
    final SearchView searchView = (SearchView) activity.findViewById(searchViewId);
    SearchableInfo searchableInfo = searchManager.getSearchableInfo(activity.getComponentName());
    searchView.setSearchableInfo(searchableInfo);
    searchView.setIconifiedByDefault(false);
    searchView.setSubmitButtonEnabled(true);
    searchView.setQueryHint("Search Here");
    searchView.setOnQueryTextListener(new SearchQueryTextListener());
  }

  @Override
  public void init(DataSource dataSource) {
    super.init(dataSource);
    listView.setTextFilterEnabled(true);
    listView.requestFocus();
    setSearchView();
  }

  class SearchQueryTextListener implements SearchView.OnQueryTextListener {

    @Override
    public boolean onQueryTextChange(String newText) {
      if (!TextUtils.isEmpty(newText)) {
        FilteredListViewAdapter filteredAdapter = (FilteredListViewAdapter) adapter;
        filteredAdapter.getFilter().filter(newText);
      }
      return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
      return false;
    }
  }

}
