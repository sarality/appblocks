package com.sarality.app.view.list;

/**
 * Compares two objects
 *
 * @author sunayna@dothat.in sunayna
 */
public interface ListItemFilter<T> {

  /**
   * If Search sequence part of the list item, then the filter criteria should return true
   *
   * @param item : List item
   * @param seq : Constraint that the list item needs to be compared with
   * @return if the seq is part of the DataObject T
   */
  public boolean passes(T item, CharSequence seq);
}
