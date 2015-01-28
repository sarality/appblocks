package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sarality.app.view.BaseViewInitializer;
import com.sarality.app.view.action.ViewAction;

import java.util.List;

/**
 * Initializer for a View that displays all rows of a list <b>without</b> a scroll bar.
 * <p/>
 * This overcomes the constraint that ListViews should not be included inside a ScrollView. A NonScrollingListView
 * is built using a LinearLayout as the root element rather than a ListView and can thus be included in a ScrollView.
 * <p/>
 * WARNING: Given that this is based a LinearLayout and rows are both inflated manually as well not reused,
 * this should only be used when the number of rows is small. Using this with a large number of rows is very
 * inefficient and may not even make sense from a UI perspective.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class NonScrollingListViewInitializer<T> extends BaseViewInitializer<LinearLayout, List<T>> {

  private final ListViewRowRenderer<T> rowRenderer;

  public NonScrollingListViewInitializer(FragmentActivity activity, LinearLayout listView,
      ListViewRowRenderer<T> rowRenderer) {
    super(activity, listView);
    this.rowRenderer = rowRenderer;
  }

  @Override
  public void render(List<T> dataList) {
    for (T data : dataList) {
      LayoutInflater inflater = LayoutInflater.from(getContext());
      View rowView = inflater.inflate(rowRenderer.getRowLayout(data), getView());
      rowRenderer.render(rowView, data);
    }
  }

  public NonScrollingListViewInitializer<T> withAction(ViewAction action) {
    registerAction(action);
    return this;
  }
}
