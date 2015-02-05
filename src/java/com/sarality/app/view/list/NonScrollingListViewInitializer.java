package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.sarality.app.view.BaseViewInitializer;
import com.sarality.app.view.ViewRenderer;
import com.sarality.app.view.action.Action;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;

import java.util.List;
import java.util.Set;

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
  private final ListActionManager actionManager;
  private EmptyListViewRenderer<?> emptyListViewRenderer;

  public NonScrollingListViewInitializer(FragmentActivity activity, LinearLayout listView,
      ListViewRowRenderer<T> rowRenderer, ListTagIdDefinition tagIdDefinition) {
    super(activity, listView);
    this.rowRenderer = rowRenderer;
    this.actionManager = new ListActionManager(tagIdDefinition);
    validateTagIdDefinition(tagIdDefinition);
  }

  public <D> NonScrollingListViewInitializer<T> withEmptyListView(View emptyView,
      ViewRenderer<View, D> emptyViewRenderer, D data) {
    this.emptyListViewRenderer = new EmptyListViewRenderer<D>(emptyView, emptyViewRenderer, data);
    return this;
  }

  public NonScrollingListViewInitializer<T> registerAction(TriggerType triggerType,
      Action<ListViewActionContext> action) {
    actionManager.register(triggerType, action);
    return this;
  }

  @Override
  public void render(List<T> dataList) {
    getView().removeAllViews();

    if (emptyListViewRenderer != null) {
      emptyListViewRenderer.render(getView(), dataList.isEmpty());
    }

    int position = 0;
    for (T data : dataList) {
      LayoutInflater inflater = LayoutInflater.from(getContext());
      View rowView = inflater.inflate(rowRenderer.getRowLayout(data), getView(), false);
      rowRenderer.render(rowView, data);
      getView().addView(rowView);
      actionManager.setup(getView(), rowView, position, rowView.getId());
      position++;
    }
  }

  public NonScrollingListViewInitializer<T> withAction(ViewAction action) {
    registerAction(action);
    return this;
  }

  @Override
  protected Set<TriggerType> getSupportedTriggerTypes() {
    return ListViewInitializer.LIST_SUPPORTED_TRIGGER_TYPES;
  }

  private void validateTagIdDefinition(ListTagIdDefinition tagIdDefinition) {
    if (tagIdDefinition == null) {
      throw new IllegalArgumentException("Must specify the Resources to be used as Ids for Tags that store the" +
          "Row position and Row Id");
    }
  }
}
