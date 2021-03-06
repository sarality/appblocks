package com.sarality.app.view.wizard;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Action to move to the previous page of the wizard.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ShowPreviousPageAction implements View.OnClickListener {
  private final FragmentWizardActivity activity;

  public ShowPreviousPageAction(FragmentWizardActivity activity) {
    this.activity = activity;
  }

  @Override
  public void onClick(View v) {
    ViewPager pager = activity.getViewPager();
    int currentItem = pager.getCurrentItem();
    if (currentItem == 0) {
      activity.callPreviousActivity();
    } else {
      pager.setCurrentItem(currentItem - 1);
    }
  }
}
