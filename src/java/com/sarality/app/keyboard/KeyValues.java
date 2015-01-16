package com.sarality.app.keyboard;

import com.sarality.app.common.collect.Maps;

import java.util.Map;

/**
 * Provides the values from the various keys in the Keyboard
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class KeyValues<T> {

  private final Map<Integer, String> keyValueMap = Maps.empty();
  private boolean isInitialized = false;

  protected void setKeyValue(int keyCode, String value) {
    keyValueMap.put(keyCode, value);
  }

  public String getValue(int keyCode) {
    String value = keyValueMap.get(keyCode);
    return value == null ? "" : value;
  }

  public boolean hasValue(int primaryCode) {
    return keyValueMap.containsKey(primaryCode);
  }

  public boolean isInitialized() {
    return isInitialized;
  }

  public void init(T data) {
    initKeyValues(data);
    isInitialized = true;
  }

  protected abstract void initKeyValues(T data);
}
