package com.sarality.app.view.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Starts new activity when a class when an item is clicked.
 * 
 * @author yogesh@ (Yogesh Kumar)
 * 
 * @param <T> Type of data object needed to start the Activity.
 */
public class StartActivityAction extends BaseViewAction {

  // The context of the class that is calling the action
  private final Context context;

  // The class that has to be opened.
  private final Class<? extends Activity> newActivityClass;

  /**
   * Constructor.
   * 
   * @param viewId Id of view that triggers the action.
   * @param triggerType Type of event that triggers the action.
   * @param context Context of the class that triggers the action.
   * @param Class Class for the Activity that has to be started.
   */
  public StartActivityAction(int viewId, TriggerType triggerType, Context context,
      Class<? extends Activity> newActivityClass) {
    super(viewId, triggerType);
    this.context = context;
    this.newActivityClass = newActivityClass;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    Intent intent = new Intent(context, newActivityClass);
    context.startActivity(intent);
    return true;
  }
}
