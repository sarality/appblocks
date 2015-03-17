package com.sarality.app.view.wizard;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

/**
 * A Page Change Listener to trigger the OnShow call on the Wizard Fragment.
 * <p/>
 * This allows Async Tasks to be executed from within teh fragment rather than moving code to Activity.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class WizardPageChangeListener implements ViewPager.OnPageChangeListener {

  private final ViewPager pager;

  WizardPageChangeListener(ViewPager pager) {
    this.pager = pager;
  }

  @Override
  public void onPageScrolled(int i, float v, int i2) {
    // Do nothing
  }

  @Override
  public void onPageSelected(int position) {
    WizardPagerAdapter pagerAdapter = (WizardPagerAdapter) pager.getAdapter();
    Fragment fragment = pagerAdapter.getItem(position);
    if (fragment instanceof WizardFragment) {
      WizardFragment wizardFragment = (WizardFragment) fragment;
      wizardFragment.onShowFragment();
    }
  }

  @Override
  public void onPageScrollStateChanged(int i) {
    // Do nothing
  }
}
