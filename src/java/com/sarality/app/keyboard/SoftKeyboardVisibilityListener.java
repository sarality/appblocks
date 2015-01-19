package com.sarality.app.keyboard;

/**
 * Interface for classes that want to listen to change in the visibility of the soft keyboard.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface SoftKeyboardVisibilityListener {

  /**
   * Action to be taken when the Soft Keyboard is visible.
   */
  public void onKeyboardVisible();
}
