package com.sarality.app.base.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiValueRegistry<K, V> extends Registry<K, List<V>> {

  public MultiValueRegistry(EntryProvider<K, V> provider) {
    super(provider.provide());
  }

  public static class EntryProvider<K, V> {
    private final Map<K, List<V>> entryMap = new HashMap<K, List<V>>();
    
    public List<Registry.Entry<K, List<V>>> provide() {
      List<Registry.Entry<K, List<V>>> list = new ArrayList<Registry.Entry<K, List<V>>>();
      for (K key : entryMap.keySet()) {
        list.add(new Registry.Entry<K, List<V>>(key, entryMap.get(key)));
      }
      return list;
    }

    protected void addEntry(K key, V value) {
      if (!entryMap.containsKey(key)) {
        entryMap.put(key, new ArrayList<V>());
      }
      entryMap.get(key).add(value);
    }
  }
}
