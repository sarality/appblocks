package com.sarality.app.view.list;

/**
 * Interface for classes that select the ListRowRenderer for a ListComponent
 * that has heterogeneous row rendering or data.
 * <p>
 * Used by the CompositeListRenderer to render such lists.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of data used to render a row.
 */
public interface ListRowRendererSelector<T> {

  /**
   * Return  the ListRowRenderer to be used for the given data.
   * 
   * @param value Data used to render a given row.
   * @return ListRowRenderer for the row.
   */
  public ListRowRenderer<T> select(T value);
}
