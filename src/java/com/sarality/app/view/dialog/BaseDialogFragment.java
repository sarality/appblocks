package com.sarality.app.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Base implementation of all types of dialog fragments.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseDialogFragment extends DialogFragment {

  private final DialogActionManager actionManager = new DialogActionManager();

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    configure(builder);
    getActionManager().setup(builder);
    Dialog dialog = builder.create();
    configure(dialog);
    return dialog;
  }

  /**
   * Retrieve ActionManager for the Fragment so that Actions can be registered for the dialog.
   *
   * @return ActionManager for the Dialog
   */
  public DialogActionManager getActionManager() {
    return actionManager;
  }

  /**
   * Configure the properties of the Dialog.
   * <p/>
   * Defines properties of the dialog by calling methods like {@link #setCancelable(boolean)}.
   *
   * @param dialog Dialog that is being rendered.
   */
  protected abstract void configure(Dialog dialog);

  /**
   * Configure the Alert Dialog Builder.
   *
   * @param builder The Builder for the Alert Dialog.
   */
  protected abstract void configure(AlertDialog.Builder builder);
}
