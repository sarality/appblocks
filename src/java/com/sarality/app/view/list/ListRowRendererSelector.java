package com.sarality.app.view.list;

/**
 * 
 * @author abhideep@sarality.com
 *
 * @param <T>
 */
public interface ListRowRendererSelector<T> {

  public ListRowRenderer<T> select(T value);
}
