package com.sarality.app.view.dialog;

import android.app.AlertDialog;

import com.sarality.app.view.action.Action;
import com.sarality.app.view.action.TriggerType;

/**
 * Wrapper for the Action to be triggered on the dialog.
 * <p/>
 * Makes it easier to store a mapping between the triggers and the actions.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class DialogAction {

  private final Action<DialogActionContext> action;
  private final TriggerType triggerType;

  DialogAction(Action<DialogActionContext> action, TriggerType triggerType) {
    this.action = action;
    this.triggerType = triggerType;
  }

  void setup(AlertDialog.Builder builder, TriggerType triggerType) {
    if (triggerType == TriggerType.DIALOG_CANCEL) {
      builder.setOnCancelListener(new DialogActionPerformer(action));
    }
  }

  TriggerType getTriggerType() {
    return triggerType;
  }
}
