package com.sarality.app.view.list;

import java.util.List;

/**
 * Interface that would sort the list in the order that the listview requires.
 *
 * @param <T> Type of data
 * @author sunayna@ (Sunayna Uberoy)
 */
public interface ListComponentSorter<T> {

  public List<T> sort(List<T> list);
}
