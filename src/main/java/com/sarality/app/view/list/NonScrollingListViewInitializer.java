package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

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
public class NonScrollingListViewInitializer<T> extends BaseListViewInitializer<LinearLayout, T> {

  private static final int DEFAULT_LOADER_ID = 0;
  private final ListTagIdDefinition tagIdDefinition;

  public NonScrollingListViewInitializer(FragmentActivity activity, LinearLayout listView,
                                         ListViewRowRenderer<T> rowRenderer, ListTagIdDefinition tagIdDefinition, int loaderId) {
    super(activity, listView, rowRenderer, loaderId);
    validateTagIdDefinition(tagIdDefinition);
    this.tagIdDefinition = tagIdDefinition;
  }

  public NonScrollingListViewInitializer(FragmentActivity activity, LinearLayout listView,
                                         ListViewRowRenderer<T> rowRenderer, ListTagIdDefinition tagIdDefinition) {
    this(activity, listView, rowRenderer, tagIdDefinition, DEFAULT_LOADER_ID);
  }

  @Override
  public void render(List<T> dataList) {
    getView().removeAllViews();
    EmptyListViewRenderer<?> emptyListViewRenderer = getEmptyListViewRenderer();
    if (emptyListViewRenderer != null) {
      emptyListViewRenderer.render(getView(), dataList.isEmpty());
    }

    ListViewRowRenderer<T> rowRenderer = getRowRenderer();
    ListViewActionManager actionManager = getActionManager();
    int position = 0;
    for (T data : dataList) {
      LayoutInflater inflater = LayoutInflater.from(getContext());
      View rowView = inflater.inflate(getRowRenderer().getRowLayout(position, data), getView(), false);
      rowRenderer.render(rowView, data);
      getView().addView(rowView);
      actionManager.setup(getView(), rowView, position, rowView.getId(), tagIdDefinition);
      position++;
    }
  }

  private void validateTagIdDefinition(ListTagIdDefinition tagIdDefinition) {
    if (tagIdDefinition == null) {
      throw new IllegalArgumentException("Must specify the Resources to be used as Ids for Tags that store the" +
          "Row position and Row Id");
    }
  }
}
