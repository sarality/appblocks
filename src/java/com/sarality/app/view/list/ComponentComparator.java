package com.sarality.app.view.list;

/**
 * Compares two objects
 *
 * @author sunayna@dothat.in sunayna
 */
public interface ComponentComparator<T> {

  public boolean compare(T item, CharSequence data);
}
