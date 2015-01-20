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
  private final ComponentComparator comparator;

  public SearchListComponentAdapter(Context context, ListRowRenderer<T> rowRenderer, List<T> rowValueList,
                                    ComponentActionManager componentManager, ComponentComparator comparator) {

    super(context, rowRenderer, rowValueList, componentManager);
    // Make a coopy of the list that will not get manipulated when the list data is changed due to search
    List<T> newList = Lists.of();
    newList.addAll(rowValueList);
    this.rowValueList = newList;
    this.comparator = comparator;
  }

  public Filter getFilter() {
    return new Filter() {

      @Override
      protected Filter.FilterResults performFiltering(CharSequence constraint) {
        String filterString = constraint.toString().toLowerCase();
        final ArrayList<T> list = new ArrayList<T>(rowValueList.size());

        for (T item : rowValueList) {
          if (comparator.compare(item, filterString)) {
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
    };
  }
}
