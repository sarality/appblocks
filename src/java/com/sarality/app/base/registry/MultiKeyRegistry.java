package com.sarality.app.base.registry;

import java.util.ArrayList;
import java.util.List;

public class MultiKeyRegistry<K, V> extends Registry<K, V> {

  public MultiKeyRegistry(EntryProvider<K,V> provider) {
    super(provider.provide());
  }

  public static class EntryProvider<K, V> {
    private final List<Registry.Entry<K, V>> list = new ArrayList<Registry.Entry<K, V>>();

    public List<Registry.Entry<K, V>> provide() {
      return list;
    }

    protected void addEntry(V value, K... keyArray) {
      if (keyArray == null || keyArray.length == 0) {
        return;
      }
      for (K key : keyArray) {
        list.add(new Registry.Entry<K,V>(key, value));
      }
    }
  }
}
