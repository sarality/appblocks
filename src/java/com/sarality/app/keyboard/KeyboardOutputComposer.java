package com.sarality.app.keyboard;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.sarality.app.view.EditorView;

/**
 * Process the keys pressed on a Keyboard and composes the out to be shown in the editor.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class KeyboardOutputComposer {

  private final EditorView editorView;
  private final KeyboardView keyboardView;

  private static final long SHIFT_LONG_PRESS_TIME_MS = 800;

  // Current State of Composer
  private long lastShiftPressedTime = 0;
  private boolean isCapsLockEnabled = false;
  private StringBuilder outputBuilder = new StringBuilder();

  public KeyboardOutputComposer(EditorView editorView, KeyboardView keyboardView) {
    this.editorView = editorView;
    this.keyboardView = keyboardView;
  }

  private InputConnection getCurrentInputConnection() {
    return editorView.getInputConnection();
  }

  private EditorInfo getCurrentInputEditorInfo() {
    return editorView.getEditorInfo();
  }

  private void commitOutput(InputConnection inputConnection) {
    if (outputBuilder.length() > 0) {
      inputConnection.commitText(outputBuilder, outputBuilder.length());
      outputBuilder.setLength(0);
    }
  }

  public boolean handleKey(int primaryCode, int[] keyCodes) {
    if (primaryCode == Keyboard.KEYCODE_DELETE) {
      handleBackspace();
    } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
      handleShift();
    } else {
      handleCharacter(primaryCode, keyCodes);
    }
    return true;
  }

  private void handleBackspace() {
    final int length = outputBuilder.length();
    if (length > 1) {
      outputBuilder.delete(length - 1, length);
      getCurrentInputConnection().setComposingText(outputBuilder, 1);
    } else if (length > 0) {
      outputBuilder.setLength(0);
      getCurrentInputConnection().commitText("", 0);
    } else {
      getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
      getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
    }
    updateShiftKeyState(getCurrentInputEditorInfo());
  }

  private void handleShift() {
    long now = System.currentTimeMillis();
    if (lastShiftPressedTime + SHIFT_LONG_PRESS_TIME_MS > now) {
      isCapsLockEnabled = !isCapsLockEnabled;
      lastShiftPressedTime = 0;
    } else {
      lastShiftPressedTime = now;
    }
  }

  private void handleCharacter(int primaryCode, int[] keyCodes) {
    if (keyboardView.isShifted()) {
      primaryCode = Character.toUpperCase(primaryCode);
    }

    if (Character.isLetter(primaryCode)) {
      outputBuilder.append((char) primaryCode);
      getCurrentInputConnection().setComposingText(outputBuilder, 1);
      updateShiftKeyState(getCurrentInputEditorInfo());
    } else {
      getCurrentInputConnection().commitText(String.valueOf((char) primaryCode), 1);
    }
  }

  private void updateShiftKeyState(EditorInfo attr) {
    // TODO(abhideep): Check if this is one of the Keyboards with Shift Key
    if (attr != null && keyboardView != null) {
      int caps = 0;
      EditorInfo editorInfo = getCurrentInputEditorInfo();
      if (editorInfo != null && editorInfo.inputType != InputType.TYPE_NULL) {
        caps = getCurrentInputConnection().getCursorCapsMode(attr.inputType);
      }
      keyboardView.setShifted(isCapsLockEnabled || caps != 0);
    }
  }

  private void handleClose() {
    commitOutput(getCurrentInputConnection());
    // requestHideSelf(0);
    keyboardView.closing();
  }
}
