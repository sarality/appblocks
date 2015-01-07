package com.sarality.app.keyboard;

import android.inputmethodservice.KeyboardView;

/**
 * Listens to keyboard actions and updates the editable text as well as the keyboard that is visible.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class KeyboardActionListener implements KeyboardView.OnKeyboardActionListener {

  private final KeyboardChanger keyboardChanger;

  public KeyboardActionListener(KeyboardChanger keyboardChanger) {
    this.keyboardChanger = keyboardChanger;
  }

  @Override
  public void onPress(int primaryCode) {
  }

  @Override
  public void onRelease(int primaryCode) {
  }

  @Override
  public void onKey(int primaryCode, int[] keyCodes) {
    keyboardChanger.handleKey(primaryCode);
  }

  @Override
  public void onText(CharSequence text) {
  }

  @Override
  public void swipeLeft() {
  }

  @Override
  public void swipeRight() {
  }

  @Override
  public void swipeDown() {
  }

  @Override
  public void swipeUp() {
  }
}
