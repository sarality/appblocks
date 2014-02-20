package com.sarality.app.list;

/**
 * 
 * @author abhideep@sarality.com
 *
 * @param <T>
 */
public interface ListRowRendererSelector<T> {

  public ListRowRenderer<T> select(T value);
}
