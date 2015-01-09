package com.sarality.app.keyboard;

import android.inputmethodservice.KeyboardView;

/**
 * Listens to keyboard actions and updates the editable text as well as the keyboard that is visible.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class KeyboardActionListener implements KeyboardView.OnKeyboardActionListener {

  private final KeyboardChanger keyboardChanger;
  private final KeyboardOutputComposer outputComposer;

  public KeyboardActionListener(KeyboardChanger keyboardChanger, KeyboardOutputComposer outputComposer) {
    this.keyboardChanger = keyboardChanger;
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
    boolean keyProcessed = keyboardChanger.handleKey(primaryCode);
    if (!keyProcessed) {
      outputComposer.handleKey(primaryCode, keyCodes);
    }
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
