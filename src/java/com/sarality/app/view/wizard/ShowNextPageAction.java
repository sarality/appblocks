package com.sarality.app.view.wizard;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Action to move to the next page of the wizard.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ShowNextPageAction implements View.OnClickListener {
  private final FragmentWizardActivity activity;
  private final WizardFragment fragment;

  public ShowNextPageAction(FragmentWizardActivity activity, WizardFragment fragment) {
    this.activity = activity;
    this.fragment = fragment;
  }

  @Override
  public void onClick(View v) {
    if (fragment.doBeforeNextFragment()) {
      ViewPager pager = activity.getViewPager();
      int currentItem = pager.getCurrentItem();
      if (currentItem < activity.getNumPages() - 1) {
        pager.setCurrentItem(currentItem + 1);
      }
    }
  }
}
