package com.sarality.app.view.dialog;

import android.content.DialogInterface;

import com.sarality.app.view.action.Action;

/**
 * Utility class to perform the action.
 * <p/>
 * This is the class that gets registered with the dialog and delegates the call to the actual action.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class DialogActionPerformer implements DialogInterface.OnCancelListener {
  private final Action<DialogActionContext> action;

  DialogActionPerformer(Action<DialogActionContext> action) {
    this.action = action;
  }

  @Override
  public void onCancel(DialogInterface dialog) {
    action.performAction(new DialogActionContext(dialog));
  }
}
