package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.action.ComponentActionManager;

/**
 * Base implementation of {@link ListRowRenderer}.
 * 
 * @author abhideep@ (Abhideep Singh)
 * 
 * @param <T>
 *          The Type of data object that is used to render the List row.
 */
public abstract class BaseListRowRenderer<T> implements ListRowRenderer<T> {

  @Override
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value,
      ComponentActionManager<T> componentManager) {
    componentManager.setupActions(rowView, value);
  }
}
