package com.sarality.app.keyboard;

import android.inputmethodservice.KeyboardView;

/**
 * Listens to keyboard actions and updates the editable text as well as the keyboard that is visible.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class KeyboardActionListener implements KeyboardView.OnKeyboardActionListener {

  private final KeyboardLayoutChanger keyboardLayoutChanger;
  private final KeyboardOutputComposer outputComposer;

  KeyboardActionListener(KeyboardLayoutChanger keyboardLayoutChanger, KeyboardOutputComposer outputComposer) {
    this.keyboardLayoutChanger = keyboardLayoutChanger;
    this.outputComposer = outputComposer;
  }

  @Override
  public void onPress(int primaryCode) {
  }

  @Override
  public void onRelease(int primaryCode) {
  }

  @Override
  public void onKey(int primaryCode, int[] keyCodes) {
    boolean keyProcessed = keyboardLayoutChanger.handleKey(primaryCode);
    if (!keyProcessed) {
      outputComposer.handleKey(primaryCode);
    }
  }

  @Override
  public void onText(CharSequence text) {
    outputComposer.handleText(text);
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
