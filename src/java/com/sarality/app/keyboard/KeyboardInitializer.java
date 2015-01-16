package com.sarality.app.keyboard;

import android.inputmethodservice.Keyboard;

import com.sarality.app.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by abhi on 14/01/15.
 */
public class KeyboardInitializer {

  private static final Logger logger = LoggerFactory.getLogger(KeyboardInitializer.class);

  private Map<String, Keyboard> keyboardMap = Maps.empty();
  private Map<String, KeyValues<?>> keyValuesMap = Maps.empty();

  void register(String keyboardName, Keyboard keyboard, KeyValues<?> keyValues) {
    keyboardMap.put(keyboardName, keyboard);
    keyValuesMap.put(keyboardName, keyValues);
  }

  public void init() {
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
    logger.error("     Looking up value for key with code " + primaryCode + " label " + key.label);
    if (keyValues.hasValue(primaryCode)) {
      String value = keyValues.getValue(primaryCode);
      String keyValue = value == null ? "" : value;
      logger.error("     Changing Key Label for " + primaryCode + " to " + keyValue);
      key.label = keyValue;
      key.text = keyValue;
    }
  }
}
