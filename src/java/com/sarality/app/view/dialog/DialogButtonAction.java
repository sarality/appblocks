package com.sarality.app.view.dialog;

import android.app.AlertDialog;

import com.sarality.app.view.action.Action;
import com.sarality.app.view.action.TriggerType;

/**
 * Wrapper for the Action to be triggered on the dialog buttons.
 * <p/>
 * Makes it easier to store a mapping between the triggers and the actions.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class DialogButtonAction {

  private final Action<DialogButtonActionContext> action;
  private final TriggerType triggerType;
  private final int buttonLabelResourceId;
  private final String buttonLabel;
  private final boolean isLabelResourceBased;

  DialogButtonAction(String buttonLabel, Action<DialogButtonActionContext> action, TriggerType triggerType) {
    this.action = action;
    this.triggerType = triggerType;
    this.buttonLabelResourceId = -1;
    this.buttonLabel = buttonLabel;
    this.isLabelResourceBased = false;
  }

  DialogButtonAction(int labelResourceId, Action<DialogButtonActionContext> action, TriggerType triggerType) {
    this.action = action;
    this.buttonLabelResourceId = labelResourceId;
    this.triggerType = triggerType;
    this.buttonLabel = null;
    this.isLabelResourceBased = true;
  }

  TriggerType getTriggerType() {
    return triggerType;
  }

  void setup(AlertDialog.Builder builder, TriggerType triggerType) {
    if (triggerType == TriggerType.DIALOG_POSITIVE) {
      if (isLabelResourceBased) {
        builder.setPositiveButton(buttonLabelResourceId, new DialogButtonActionPerformer(action));
      } else {
        builder.setPositiveButton(buttonLabel, new DialogButtonActionPerformer(action));
      }
    } else if (triggerType == TriggerType.DIALOG_NEGATIVE) {
      if (isLabelResourceBased) {
        builder.setNegativeButton(buttonLabelResourceId, new DialogButtonActionPerformer(action));
      } else {
        builder.setNegativeButton(buttonLabel, new DialogButtonActionPerformer(action));
      }
    } else if (triggerType == TriggerType.DIALOG_NEUTRAL) {
      if (isLabelResourceBased) {
        builder.setNegativeButton(buttonLabelResourceId, new DialogButtonActionPerformer(action));
      } else {
        builder.setNegativeButton(buttonLabel, new DialogButtonActionPerformer(action));
      }
    }
  }
}
