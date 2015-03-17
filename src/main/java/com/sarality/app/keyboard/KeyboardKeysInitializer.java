package com.sarality.app.keyboard;

import android.inputmethodservice.Keyboard;

import com.sarality.app.common.collect.Maps;

import java.util.Map;

/**
 * Initializes a keyboard instance with specific values for text and labels.
 * <p/>
 * Used for generation of dynamic contextual keyboards.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class KeyboardKeysInitializer {

  private Map<String, Keyboard> keyboardMap = Maps.empty();
  private Map<String, KeyValues<?>> keyValuesMap = Maps.empty();

  void register(String keyboardName, Keyboard keyboard, KeyValues<?> keyValues) {
    keyboardMap.put(keyboardName, keyboard);
    keyValuesMap.put(keyboardName, keyValues);
  }

  void init() {
    for (String keyboardName : keyboardMap.keySet()) {
      Keyboard keyboard = keyboardMap.get(keyboardName);
      KeyValues<?> keyValues = keyValuesMap.get(keyboardName);
      if (keyValues != null) {
        if (keyValues.isInitialized()) {
          for (Keyboard.Key key : keyboard.getKeys()) {
            setKeyLabel(key, keyValues);
          }
        } else {
          throw new IllegalStateException("Keyboard Initializer called before KeyValues initialization for keyboard "
              + keyboardName);
        }
      }
    }
  }

  private void setKeyLabel(Keyboard.Key key, KeyValues keyValues) {
    int[] codes = key.codes;
    if (codes == null || codes.length < 1) {
      return;
    }
    int primaryCode = codes[0];
    if (keyValues.hasValue(primaryCode)) {
      String value = keyValues.getValue(primaryCode);
      String keyValue = value == null ? "" : value;
      key.label = keyValue;
      key.text = keyValue;
    }
  }
}
