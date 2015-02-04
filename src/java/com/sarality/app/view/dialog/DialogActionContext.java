package com.sarality.app.view.dialog;

import android.content.DialogInterface;

/**
 * Context to perform an Action on a Dialog.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DialogActionContext {

  private final DialogInterface dialog;

  public DialogActionContext(DialogInterface dialog) {
    this.dialog = dialog;
  }

  public DialogInterface getDialog() {
    return dialog;
  }
}
