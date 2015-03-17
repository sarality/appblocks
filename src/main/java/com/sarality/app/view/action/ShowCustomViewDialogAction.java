package com.sarality.app.view.action;

import android.support.v4.app.Fragment;

import com.sarality.app.view.dialog.CustomViewDialogFragment;

/**
 * Shows the  Dialog
 *
 * @author sunayna@dothat.in sunayna
 */
public class ShowCustomViewDialogAction extends AppViewAction {

  private final CustomViewDialogFragment dialogFragment;
  private final Fragment fragment;

  public ShowCustomViewDialogAction(Fragment fragment, CustomViewDialogFragment dialogFragment) {
    this.dialogFragment = dialogFragment;
    this.fragment = fragment;
  }

  @Override
  protected boolean doAction() {
    dialogFragment.show(fragment.getFragmentManager(), " ");
    return true;
  }
}
