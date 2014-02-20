package com.sarality.app.list;

import java.util.HashMap;
import java.util.Map;

public class ClassBasedRendererSelector<T> implements ListRowRendererSelector<T> {

  private final Map<Class<? extends T>, ListRowRenderer<T>> rendererMap = 
      new HashMap<Class<? extends T>, ListRowRenderer<T>>();

  public ClassBasedRendererSelector<T> register(Class<? extends T> valueClass, 
      ListRowRenderer<T> renderer) {
    rendererMap.put(valueClass, renderer);
    return this;
  }

  @Override
  public ListRowRenderer<T> select(T value) {
    if (!rendererMap.containsKey(value.getClass())) {
      throw new IllegalStateException("No Renderer regsited for class " + value.getClass().getName());
    }
    return rendererMap.get(value.getClass());
  }

}
