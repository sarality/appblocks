package com.sarality.app.loader;

import com.sarality.app.common.collect.Maps;
import com.sarality.app.data.DataObject;

import java.util.Map;

/**
 * Indexes a data-builder pair (or a list of data-builder pairs) so that the can be accessed by a key.
 * <p/>
 * Used for setting associated data on a builder for the given data.
 *
 * @param <T> Type of data to indexed.
 * @param <B> Type of builder for the data to indexed.
 * @param <K> Type of key used for indexing.
 * @author abhideep@ (Abhideep Singh)
 */
public class DataBuilderIndexer<T, B, K> {

  private final Map<K, B> builderIndex = Maps.empty();
  private final FieldValueGetter<T, K> fieldValueGetter;

  public DataBuilderIndexer(FieldValueGetter<T, K> fieldValueGetter) {
    this.fieldValueGetter = fieldValueGetter;
  }

  public void index(T data, B dataBuilder) {
    K key = getKey(data);
    builderIndex.put(key, dataBuilder);
  }

  public K getKey(T data) {
    return fieldValueGetter.getValue(data);
  }

  public B getBuilder(T data) {
    K key = getKey(data);
    return getBuilderForKey(key);
  }

  public B getBuilderForKey(K key) {
    if (key == null) {
      return null;
    }
    return builderIndex.get(key);
  }

}
