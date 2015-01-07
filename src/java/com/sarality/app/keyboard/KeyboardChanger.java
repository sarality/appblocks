package com.sarality.app.keyboard;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;

import java.util.HashMap;
import java.util.Map;

/**
 * Changes the Keyboard to be displayed in the given KeyboardView based on the mode that the keyboard is in.
 * <p />
 * Allows the keyboard to be switched from Assistant Mode to Normal mode.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class KeyboardChanger {

  private final KeyboardView keyboardView;
  private final Map<Integer, Keyboard> keyboardMap = new HashMap<Integer, Keyboard>();

  /**
   * Constructor.
   *
   * @param keyboardView The Keyboard View that this changes the keyboard for.
   */
  public KeyboardChanger(KeyboardView keyboardView) {
    this.keyboardView = keyboardView;
  }

  /**
   * Register the Keyboard to use when the key with the given primary code is pressed.
   *
   * @param primaryCode The code for the key that is pressed.
   * @param keyboard The keyboard to display.
   */
  public void registerKeyboard(int primaryCode, Keyboard keyboard) {
    keyboardMap.put(primaryCode, keyboard);
  }

  /**
   * Handles the Key that was pressed on the Keyboard
   *
   * @param primaryCode The code for the key that is pressed.
   * @return {@code true} with Key can be handled by the class, {@code false} otherwise.
   */
  public boolean handleKey(int primaryCode) {
    Keyboard keyboard = keyboardMap.get(primaryCode);
    if (keyboard != null) {
      keyboardView.setKeyboard(keyboard);
      return true;
    }
    return false;
  }
}
