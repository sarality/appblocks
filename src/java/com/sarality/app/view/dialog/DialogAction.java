package com.sarality.app.view.dialog;

import android.content.DialogInterface;

import com.sarality.app.view.action.BaseAction;

/**
 * Base implementation for an Action on a Dialog.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class DialogAction extends BaseAction<DialogActionContext>
    implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener {

  private DialogInterface dialog;
  private int buttonType;

  @Override
  public void onCancel(DialogInterface dialog) {
    setActionContext(new DialogActionContext(dialog));
    perform();
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    setActionContext(new DialogActionContext(dialog, which));
    perform();
  }

  @Override
  public void onDismiss(DialogInterface dialogInterface) {
    setActionContext(new DialogActionContext(dialog));
    perform();
  }
}
