package com.sarality.app.view.dialog;

import android.content.DialogInterface;

/**
 * Context to perform an Action on a Dialog.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DialogActionContext {

  public static final int NON_BUTTON_ACTION = -1;

  private final DialogInterface dialog;
  private int buttonType;

  public DialogActionContext(DialogInterface dialog) {
    this.dialog = dialog;
    this.buttonType = NON_BUTTON_ACTION;
  }

  public DialogActionContext(DialogInterface dialog, int which) {
    this.dialog = dialog;
    this.buttonType = which;
  }

  public DialogInterface getDialog() {
    return dialog;
  }

  public int getButtonType() {
    return buttonType;
  }

}
