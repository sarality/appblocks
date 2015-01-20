package com.sarality.app.view.list;

/**
 * Filters the list of objects
 *
 * @author sunayna@dothat.in sunayna
 */
public interface ListItemFilter<T> {

  public boolean passes(T item, CharSequence seq);
}
