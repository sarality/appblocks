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
public class DialogButtonActionPerformer implements DialogInterface.OnClickListener {
  private final Action<DialogButtonActionContext> action;

  public DialogButtonActionPerformer(Action<DialogButtonActionContext> action) {
    this.action = action;
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    action.performAction(new DialogButtonActionContext(dialog, which));
  }
}
