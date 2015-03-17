package com.sarality.app.view.wizard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base implementation for a Page in the Wizard.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class WizardFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(getContentViewId(), container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    FragmentWizardActivity activity = (FragmentWizardActivity) getActivity();

    View nextButton = getNextButtonView();
    if (nextButton != null) {
      nextButton.setOnClickListener(new ShowNextPageAction(activity, this));
    }

    View backButton = getBackButtonView();
    if (backButton != null) {
      backButton.setOnClickListener(new ShowPreviousPageAction(activity));
    }
  }

  public View getView(int viewId) {
    return getView().findViewById(viewId);
  }

  public void onShowFragment() {
    // Actions to perform when the Fragment is displayed;
  }

  public boolean doBeforeNextFragment() {
    // Actions to perform before the Next Fragment is displayed;
    return true;
  }

  public abstract int getContentViewId();

  public abstract View getNextButtonView();

  public abstract View getBackButtonView();

}
