package com.sarality.app.view.dialog;

import android.content.DialogInterface;

import com.sarality.app.view.action.BaseAction;

/**
 * Base implementation for an Action on a Dialog.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class DialogAction extends BaseAction<DialogAction> implements DialogInterface.OnCancelListener,
    DialogInterface.OnClickListener {

  private DialogInterface dialog;
  private int buttonType;

  @Override
  public void onCancel(DialogInterface dialog) {
    setDialog(dialog);
    perform();
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    setDialog(dialog);
    setButtonType(which);
    perform();
  }

  @Override
  public void setContext(DialogAction action) {
    setDialog(action.getDialog());
  }

  @Override
  protected DialogAction getInstance() {
    return this;
  }

  protected DialogInterface getDialog() {
    return dialog;
  }

  protected void setDialog(DialogInterface dialog) {
    this.dialog = dialog;
  }

  protected int getButtonType() {
    return buttonType;
  }

  protected void setButtonType(int which) {
    this.buttonType = which;
  }
}
