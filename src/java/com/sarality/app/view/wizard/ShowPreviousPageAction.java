package com.sarality.app.view.wizard;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.dothat.app.module.setup.SetupWizardActivity;

/**
 * Action to move to the next page of the wizard.
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
      activity.onBackPressed();
    } else {
      pager.setCurrentItem(currentItem - 1);
    }
  }
}
