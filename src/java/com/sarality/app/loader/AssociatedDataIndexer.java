package com.sarality.app.loader;

import com.sarality.app.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Indexes a data or a list of data so that we can look it by a key.
 * <p/>
 * Assumes non-unique indexing i.e. more than one object can map to the same key.
 *
 * @param <K> Type of key used for indexing.
 * @param <T> Type of data to indexed.
 * @author abhideep@ (Abhideep Singh)
 */
public class AssociatedDataIndexer<T, K> {

  private final Map<K, List<T>> index = Maps.empty();
  private final FieldValueGetter<T, K> keyValueExtractor;

  /**
   * Constructor.
   *
   * @param keyValueExtractor Extractor to retrieve the the index key value.
   */
  public AssociatedDataIndexer(FieldValueGetter<T, K> keyValueExtractor) {
    this.keyValueExtractor = keyValueExtractor;
  }

  /**
   * Add the data to the index.
   *
   * @param data Data to be added to the index.
   */
  public void index(T data) {
    K key = getKey(data);
    if (index.get(key) == null) {
      index.put(key, new ArrayList<T>());
    }
    index.get(key).add(data);
  }

  /**
   * Add all data in the list to the index.
   *
   * @param dataList List of data to be added the index.
   */
  public void indexAll(List<T> dataList) {
    for (T data : dataList) {
      index(data);
    }
  }

  /**
   * Get the Index Key value for the given data object.
   *
   * @param data The data for which the key is needed.
   * @return Index Key value for the data.
   */
  public K getKey(T data) {
    return keyValueExtractor.getValue(data);
  }

  /**
   * Get all values that we indexed for given key.
   *
   * @param key Key to lookup values for.
   * @return List of values indexed by the key.
   */
  public List<T> getValues(K key) {
    if (key == null) {
      return null;
    }
    return index.get(key);
  }
}
