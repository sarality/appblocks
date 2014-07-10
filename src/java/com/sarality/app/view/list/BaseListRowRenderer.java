package com.sarality.app.view.list;

import android.util.SparseArray;
import android.view.View;

import com.sarality.app.view.action.ComponentActionManager;

/**
 * Base implementation of {@link ListRowRenderer}.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T> The Type of data object that is used to render the List row.
 */
public abstract class BaseListRowRenderer<T> implements ListRowRenderer<T> {

  protected SparseArray<Object> tagList = new SparseArray<Object>();

  @Override
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value, ComponentActionManager componentManager) {
    componentManager.setupActions(rowView);
  }

  @Override
  public void setTagList(int viewId, Object value) {
    this.tagList.put(viewId, value);
  }

  protected void setTags(View view) {
    for (int i = 0; i < tagList.size(); i++) {
      view.setTag(tagList.keyAt(i), tagList.valueAt(i));
    }
  }
}
