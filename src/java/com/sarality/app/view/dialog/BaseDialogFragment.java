package com.sarality.app.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Base implementation of all types of dialog fragments.
 * <p/>
 * TODO: Add Action support.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseDialogFragment extends DialogFragment {

  private final FragmentActivity activity;

  /**
   * Constructor.
   *
   * @param activity Fragment activity that instantiated the fragment.
   */
  public BaseDialogFragment(FragmentActivity activity) {
    this.activity = activity;
  }

  /**
   * @return Fragment activity that instantiated the fragment.
   */
  protected FragmentActivity getFragmentActivity() {
    return activity;
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
   * @return The Builder for the Alert Dialog.
   */
  protected abstract AlertDialog.Builder configureAlertDialogBuilder(AlertDialog.Builder builder);


  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    configureAlertDialogBuilder(builder);
    Dialog dialog = builder.create();
    configure(dialog);
    return dialog;
  }
}
