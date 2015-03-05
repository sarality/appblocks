package com.sarality.app.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

/**
 * Manages the registration and setup of Actions of a Dialog.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DialogActionManager {

  private DialogButtonAction positiveButtonAction;
  private DialogButtonAction negativeButtonAction;
  private DialogButtonAction neutralButtonAction;
  private DialogButtonAction dismissButtonAction;

  public void setPositiveButtonAction(int labelId, DialogAction action) {
    setPositiveButtonAction(new DialogButtonAction(labelId, action));
  }

  public void setPositiveButtonAction(int labelId, DialogInterface.OnClickListener listener) {
    setPositiveButtonAction(new DialogButtonAction(labelId, new DialogOnClickListenerAction(listener)));
  }

  public void setPositiveButtonAction(String label, DialogAction action) {
    setPositiveButtonAction(new DialogButtonAction(label, action));
  }

  public void setPositiveButtonAction(String label, DialogInterface.OnClickListener listener) {
    setPositiveButtonAction(new DialogButtonAction(label, new DialogOnClickListenerAction(listener)));
  }

  public void setNegativeButtonAction(int labelId, DialogAction action) {
    setNegativeButtonAction(new DialogButtonAction(labelId, action));
  }

  public void setNegativeButtonAction(int labelId, DialogInterface.OnClickListener listener) {
    setNegativeButtonAction(new DialogButtonAction(labelId, new DialogOnClickListenerAction(listener)));
  }

  public void setNegativeButtonAction(String label, DialogAction action) {
    setNegativeButtonAction(new DialogButtonAction(label, action));
  }

  public void setNegativeButtonAction(String label, DialogInterface.OnClickListener listener) {
    setNegativeButtonAction(new DialogButtonAction(label, new DialogOnClickListenerAction(listener)));
  }

  public void setNeutralButtonAction(int labelId, DialogAction action) {
    setNeutralButtonAction(new DialogButtonAction(labelId, action));
  }

  public void setNeutralButtonAction(int labelId, DialogInterface.OnClickListener listener) {
    setNeutralButtonAction(new DialogButtonAction(labelId, new DialogOnClickListenerAction(listener)));
  }

  public void setNeutralButtonAction(String label, DialogAction action) {
    setNeutralButtonAction(new DialogButtonAction(label, action));
  }

  public void setNeutralButtonAction(String label, DialogInterface.OnClickListener listener) {
    setNeutralButtonAction(new DialogButtonAction(label, new DialogOnClickListenerAction(listener)));
  }

  public void setDismissButtonAction(int labelId, DialogAction action) {
    setDismissButtonAction(new DialogButtonAction(labelId, action));
  }

  public void setDismissButtonAction(String label, DialogAction action) {
    setDismissButtonAction(new DialogButtonAction(label, action));
  }

  public void setDismissButtonAction(int labelId, DialogInterface.OnDismissListener listener) {
    setDismissButtonAction(new DialogButtonAction(labelId, new DialogOnDismissListenerAction(listener)));
  }

  public void setDismissButtonAction(String label, DialogInterface.OnDismissListener listener) {
    setDismissButtonAction(new DialogButtonAction(label, new DialogOnDismissListenerAction(listener)));
  }

  void setup(AlertDialog.Builder builder) {
    if (positiveButtonAction != null) {
      if (positiveButtonAction.isLabelResourceBased()) {
        builder.setPositiveButton(positiveButtonAction.getButtonLabelResourceId(), positiveButtonAction.getAction());
      } else {
        builder.setPositiveButton(positiveButtonAction.getButtonLabel(), positiveButtonAction.getAction());
      }
    }

    if (negativeButtonAction != null) {
      if (negativeButtonAction.isLabelResourceBased()) {
        builder.setNegativeButton(negativeButtonAction.getButtonLabelResourceId(), negativeButtonAction.getAction());
      } else {
        builder.setNegativeButton(negativeButtonAction.getButtonLabel(), negativeButtonAction.getAction());
      }
    }

    if (neutralButtonAction != null) {
      if (neutralButtonAction.isLabelResourceBased()) {
        builder.setNeutralButton(neutralButtonAction.getButtonLabelResourceId(), neutralButtonAction.getAction());
      } else {
        builder.setNeutralButton(neutralButtonAction.getButtonLabel(), neutralButtonAction.getAction());
      }
    }
  }

  public void setup(Dialog dialog) {
    if (dismissButtonAction != null) {
      dialog.setOnDismissListener(dismissButtonAction.getAction());
    }
  }

  private void setPositiveButtonAction(DialogButtonAction action) {
    if (this.positiveButtonAction != null) {
      throw new IllegalStateException("Trying to register multiple Actions for Positive Button on Dialog. " +
          "Use action chaining instead by all calling registerSuccessAction");
    }
    this.positiveButtonAction = action;
  }

  private void setNegativeButtonAction(DialogButtonAction action) {
    if (this.negativeButtonAction != null) {
      throw new IllegalStateException("Trying to register multiple Actions for Negative Button on Dialog. " +
          "Use action chaining instead by all calling registerSuccessAction");
    }
    this.negativeButtonAction = action;
  }

  private void setNeutralButtonAction(DialogButtonAction action) {
    if (this.neutralButtonAction != null) {
      throw new IllegalStateException("Trying to register multiple Actions for Neutral Button on Dialog. " +
          "Use action chaining instead by all calling registerSuccessAction");
    }
    this.neutralButtonAction = action;
  }

  private void setDismissButtonAction(DialogButtonAction action) {
    if (this.dismissButtonAction != null) {
      throw new IllegalStateException("Trying to register multiple Actions for Dismiss Button on Dialog. " +
          "Use action chaining instead by all calling registerSuccessAction");
    }
    this.dismissButtonAction = action;
  }

  private class DialogButtonAction {

    private final DialogAction action;
    private final int buttonLabelResourceId;
    private final String buttonLabel;
    private final boolean isLabelResourceBased;

    private DialogButtonAction(String buttonLabel, DialogAction action) {
      this.buttonLabelResourceId = -1;
      this.buttonLabel = buttonLabel;
      this.isLabelResourceBased = false;
      this.action = action;
    }

    private DialogButtonAction(int labelResourceId, DialogAction action) {
      this.buttonLabelResourceId = labelResourceId;
      this.buttonLabel = null;
      this.isLabelResourceBased = true;
      this.action = action;
    }

    private int getButtonLabelResourceId() {
      return buttonLabelResourceId;
    }

    private String getButtonLabel() {
      return buttonLabel;
    }

    private boolean isLabelResourceBased() {
      return isLabelResourceBased;
    }

    private DialogAction getAction() {
      return action;
    }
  }

  private class DialogOnClickListenerAction extends DialogAction {

    private final DialogInterface.OnClickListener listener;

    DialogOnClickListenerAction(DialogInterface.OnClickListener listener) {
      this.listener = listener;
    }

    @Override
    protected boolean doAction() {
      DialogActionContext context = getActionContext();
      listener.onClick(context.getDialog(), context.getButtonType());
      return true;
    }
  }

  private class DialogOnDismissListenerAction extends DialogAction {

    private final DialogInterface.OnDismissListener listener;

    DialogOnDismissListenerAction(DialogInterface.OnDismissListener listener) {
      this.listener = listener;
    }

    @Override
    protected boolean doAction() {
      DialogActionContext context = getActionContext();
      listener.onDismiss(context.getDialog());
      return true;
    }
  }
}
