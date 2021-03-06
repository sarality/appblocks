package com.sarality.app.view.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.sarality.app.view.action.Action;
import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Starts new activity when a class when an item is clicked.
 * 
 * @author yogesh@ (Yogesh Kumar)
 */
public class StartActivityAction extends BaseViewAction {

  // The context of the class that is calling the action
  protected final Context context;

  // The class that has to be opened.
  private final Class<? extends Activity> newActivityClass;
  private BundleGenerator bundleGenerator = null;

  /**
   * Constructor.
   * 
   * @param viewId Id of view that triggers the action.
   * @param triggerType Type of event that triggers the action.
   * @param context Context of the class that triggers the action.
   */
  public StartActivityAction(int viewId, TriggerType triggerType, Context context,
      Class<? extends Activity> newActivityClass) {
    this(viewId, triggerType, context, newActivityClass, null);
  }

  /**
   * Constructor.
   * 
   * @param viewId Id of view that triggers the action.
   * @param triggerType Type of event that triggers the action.
   * @param context Context of the class that triggers the action.
   */
  public StartActivityAction(int viewId, TriggerType triggerType, Context context,
      Class<? extends Activity> newActivityClass, BundleGenerator bundleGenerator) {
    super(viewId, triggerType);
    this.context = context;
    this.newActivityClass = newActivityClass;
    this.bundleGenerator = bundleGenerator;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    Intent intent = new Intent(context, newActivityClass);
    if (bundleGenerator != null) {
      intent.putExtras(bundleGenerator.generate(view));
    }
    context.startActivity(intent);
    return true;
  }

}
