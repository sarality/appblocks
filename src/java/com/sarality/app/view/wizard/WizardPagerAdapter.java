package com.sarality.app.view.wizard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sarality.app.common.collect.Lists;

import java.util.List;

/**
 * Adapter that provides the Fragments for each step of the Wizard
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class WizardPagerAdapter extends FragmentStatePagerAdapter {

  private final List<Fragment> fragmentList = Lists.emptyList();

  public WizardPagerAdapter(FragmentManager manager) {
    super(manager);
  }

  public WizardPagerAdapter withSteps(List<Fragment> fragments) {
    if (fragments != null) {
      fragmentList.addAll(fragments);
    }
    return this;
  }

  @Override
  public Fragment getItem(int position) {
    if (position >= fragmentList.size()) {
      throw new IllegalStateException("Attempting to get Fragment for position " + position
          + " when maximum number of pages is " + fragmentList.size());
    }
    return fragmentList.get(position);
  }

  @Override
  public int getCount() {
    return fragmentList.size();
  }
}
