package com.sarality.app.view.fragment.action;

import android.os.Bundle;
import android.view.View;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;
import com.sarality.app.view.fragment.RefreshableFragment;
import com.sarality.app.view.nav.BundleGenerator;

/**
 * Refreshes the Refreshablefragment
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public class RefreshFragmentAction extends BaseViewAction {

  // Current fragment that will be refreshed
  private final RefreshableFragment fragment;
  private final BundleGenerator bundleGenerator;

  /**
   * Constructor.
   *
   * @param viewId Id of the View that triggered the action.
   * @param triggerType The type of trigger.
   * @param fragment Fragment that will be refreshed
   */
  public RefreshFragmentAction(int viewId, TriggerType triggerType, RefreshableFragment fragment,
                               BundleGenerator bundleGenerator) {
    super(viewId, triggerType);
    this.fragment = fragment;
    this.bundleGenerator = bundleGenerator;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    Bundle bundle = bundleGenerator.generate(view);
    fragment.refresh(bundle);
    return true;
  }
}
