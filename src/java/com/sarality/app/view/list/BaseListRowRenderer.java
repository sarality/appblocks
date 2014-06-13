package com.sarality.app.view.list;

import java.util.List;

import android.view.View;

import com.sarality.app.view.action.ComponentAction;
import com.sarality.app.view.action.ViewAction;

/**
 * Base implementation of {@link ListRowRenderer}.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> The Type of data object that is used to render the List row.
 */
public abstract class BaseListRowRenderer<T> implements ListRowRenderer<T> {

  @Override
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value, List<ViewAction<T>> actionList) {
    ComponentAction<T> componentAction = new ComponentAction<T>();
    componentAction.setupActions(actionList, rowView, value);
  }
}
