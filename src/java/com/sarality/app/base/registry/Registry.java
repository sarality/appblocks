package com.sarality.app.base.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registry<K, V> {
  private final Map<K, V> registry = new HashMap<K, V>();

  public Registry() {
    // Empty registry
  }

  public void register(List<Entry<K, V>> entryList) {
    for (Entry<K, V> entry : entryList) {
      registry.put(entry.getKey(), entry.getValue());
    }    
  }

  public void register(EntryProvider<K, V> entryProvider) {
    register(entryProvider.provide());
  }

  public V lookup(K key) {
    return registry.get(key);
  }

  public static class EntryProvider<K, V> {
    private final List<Registry.Entry<K, V>> list = new ArrayList<Registry.Entry<K, V>>();

    public List<Registry.Entry<K, V>> provide() {
      return list;
    }

    
    protected void addEntry(K key, V value) {
      list.add(new Registry.Entry<K, V>(key, value));
    }    
  }

  public static class Entry<K, V> {

    private final K key;
    private final V value;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
    
    public final K getKey() {
      return key;
    }
    
    public final V getValue() {
      return value;
    }
  }

}
