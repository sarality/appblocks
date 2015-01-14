package com.sarality.app.keyboard;

/**
 * Interface for classes that want to listen to change in the visibility of a keyboard.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface KeyboardStateChangeListener {

  /**
   * Action to be taken when the Keyboard is Visible.
   */
  public void onKeyboardVisible();
}
