package com.sarality.app.view.list;

import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.action.ComponentActionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom adapter that can filter the list and create new list
 *
 * @author sunayna@dothat.in sunayna
 */
public class SearchListComponentAdapter<T> extends ListComponentAdapter<T> implements Filterable {

  private final List<T> rowValueList;
  private final ListItemFilter<T> listItemFilter;
  private final SearchListFilter filter;

  public SearchListComponentAdapter(Context context, ListRowRenderer<T> rowRenderer, List<T> rowValueList,
                                    ComponentActionManager componentManager, ListItemFilter<T> listItemFilter) {

    super(context, rowRenderer, rowValueList, componentManager);
    this.rowValueList = Lists.of();
    this.rowValueList.addAll(rowValueList);
    this.listItemFilter = listItemFilter;
    this.filter = new SearchListFilter();
  }

  @Override
  public Filter getFilter() {
    return filter;
  }

  class SearchListFilter extends Filter {
    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
      String filterString = constraint.toString().toLowerCase();
      final ArrayList<T> list = new ArrayList<T>();

      for (T item : rowValueList) {
        if (listItemFilter.passes(item, filterString)) {
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
      reinitalize((List<T>) results.values);
    }
  }
}
