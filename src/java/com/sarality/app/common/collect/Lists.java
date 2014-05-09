package com.sarality.app.common.collect;

import java.util.ArrayList;
import java.util.List;

public class Lists {

  public static <T> List<T> of(T... elements) {
    List<T> list = new ArrayList<T>();
    for (T element : elements) {
      list.add(element);
    }
    return list;
  }
}
