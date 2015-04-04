package com.sarality.app.view.wizard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.sarality.app.view.action.ViewActionManager;

import java.util.List;

/**
 * Base implementation for Activity for a Wizard that uses a fragments for each step.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class FragmentWizardActivity extends FragmentActivity {

  /**
   * The View pager with all steps of the wizard as fragments.
   */
  private ViewPager pager;

  /**
   * The pager adapter, which provides the pages to the view pager.
   */
  private WizardPagerAdapter pagerAdapter;

  private ViewActionManager actionManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentViewId());

    actionManager = new ViewActionManager(this);
    // Instantiate a ViewPager and a PagerAdapter.
    pager = (ViewPager) findViewById(getViewPagerId());
    pagerAdapter = new WizardPagerAdapter(getSupportFragmentManager())
        .withSteps(getFragments());
    pager.setAdapter(pagerAdapter);
    pager.setOnPageChangeListener(new WizardPageChangeListener(pager));
    int wizardTitle = getWizardTitle();
    if (wizardTitle > 0) {
      setTitle(getWizardTitle());
    }
  }

  protected ViewActionManager getActionManager() {
    return actionManager;
  }

  @Override
  public void onBackPressed() {
    new ShowPreviousPageAction(this).onClick(null);
  }

  protected void callPreviousActivity() {
    super.onBackPressed();
  }

  ViewPager getViewPager() {
    return pager;
  }

  int getNumPages() {
    return pagerAdapter.getCount();
  }

  public abstract int getContentViewId();

  public abstract int getViewPagerId();

  public abstract int getWizardTitle();

  public abstract List<Fragment> getFragments();
}
