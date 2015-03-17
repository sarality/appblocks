package com.sarality.app.view.list;

import java.util.HashMap;
import java.util.Map;

/**
 * Selects the {@link ListRowRenderer} based on the class of the data
 * that needs to be rendered.
 * <p>
 * The Renderer must be registered at initialized time and then is used
 * by the {@link CompositeListRowRenderer} to figure out how to render the row.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of data object that is being rendered in each row.
 */
public class ClassBasedRendererSelector<T> implements ListRowRendererSelector<T> {

  /**
   * Map between class and the type of renderer to use for a data of that class.
   */
  private final Map<Class<? extends T>, ListRowRenderer<T>> rendererMap = 
      new HashMap<Class<? extends T>, ListRowRenderer<T>>();

  /**
   * Define a mapping between the class and the renderer to be used for any data 
   * object of that type.
   * 
   * @param valueClass Class for which the Renderer must be used.
   * @param renderer The renderer to be used.
   * @return The Selector itself - enables chaining of registration.
   */
  public ClassBasedRendererSelector<T> register(Class<? extends T> valueClass, 
      ListRowRenderer<T> renderer) {
    rendererMap.put(valueClass, renderer);
    return this;
  }

  @Override
  public ListRowRenderer<T> select(T value) {
    if (!rendererMap.containsKey(value.getClass())) {
      throw new IllegalStateException("No Renderer registered for class " + value.getClass().getName());
    }
    return rendererMap.get(value.getClass());
  }
}
