package com.sarality.app.view.dialog;

import android.content.DialogInterface;

/**
 * Context to perform an Action on a Dialog Button.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DialogButtonActionContext extends DialogActionContext {

  private final int buttonPosition;

  public DialogButtonActionContext(DialogInterface dialog, int buttonPosition) {
    super(dialog);
    this.buttonPosition = buttonPosition;
  }

  public int getButtonPosition() {
    return buttonPosition;
  }
}
