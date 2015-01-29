package com.sarality.app.view.list;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to display ListView where rows can be filtered based on user entered search terms.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class FilteredListViewAdapter<T> extends ListViewAdapter<T> implements Filterable {

  private final Filter filter;

  public FilteredListViewAdapter(Context context, List<T> rowValueList, ListViewRowRenderer<T> rowRenderer,
      ListItemFilter<T> listItemFilter) {
    super(context, rowValueList, rowRenderer);
    List<T> clonedRowValueList = new ArrayList<T>();
    clonedRowValueList.addAll(rowValueList);
    this.filter = new ListViewFilter(listItemFilter, clonedRowValueList);
  }

  @Override
  public Filter getFilter() {
    return filter;
  }

  private class ListViewFilter extends Filter {
    private final ListItemFilter<T> listItemFilter;
    private final List<T> rowValueList;

    private ListViewFilter(ListItemFilter<T> listItemFilter, List<T> rowValueList) {
      this.listItemFilter = listItemFilter;
      this.rowValueList = rowValueList;
    }

    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
      final ArrayList<T> list = new ArrayList<T>();
      for (T item : rowValueList) {
        if (listItemFilter.passes(item, constraint)) {
          list.add(item);
        }
      }

      FilterResults results = new FilterResults();
      results.values = list;
      results.count = list.size();
      return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
      reinitialize((List<T>) results.values);
    }
  }
}
