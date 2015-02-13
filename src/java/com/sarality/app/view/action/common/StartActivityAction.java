package com.sarality.app.view.action.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sarality.app.view.action.AppViewAction;
import com.sarality.app.view.nav.BundleGenerator;

/**
 * Actions that starts new activity with the generated intent.
 * 
 * @author abhideep@ (Abhideep )
 */
public class StartActivityAction extends AppViewAction {

  private final Class<? extends Activity> activityClass;
  private final BundleGenerator bundleGenerator;

  /**
   * Constructor.
   *
   * @param activityClass Class for the activity that needs to started.
   */
  public StartActivityAction(Class<? extends Activity> activityClass) {
    this(activityClass, null);
  }

  /**
   * Constructor.
   *
   * @param activityClass Class for the activity that needs to started.
   * @param bundleGenerator Class that generates the bundle with extra parameters for the Intent.
   */
  public StartActivityAction(Class<? extends Activity> activityClass, BundleGenerator bundleGenerator) {
    this.activityClass = activityClass;
    this.bundleGenerator = bundleGenerator;
  }

  @Override
  public boolean doAction() {
    Context context = getView().getContext();
    Intent intent = new Intent(context, activityClass);
    if (bundleGenerator != null) {
      intent.putExtras(bundleGenerator.generate(getView()));
    }
    context.startActivity(intent);
    return true;
  }
}
