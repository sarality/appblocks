package com.sarality.app.view.nav;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.sarality.app.view.action.TriggerType;

/**
 * Starts the Activity by passing in the parameters in the intent
 * 
 * @author sunayna(Sunayna Uberoy)
 */
public class StartActivityWithParamsAction extends StartActivityAction {

  private final String tag;

  public StartActivityWithParamsAction(int viewId, TriggerType triggerType, Context context,
      Class<? extends Activity> newActivityClass, String tag) {
    super(viewId, triggerType, context, newActivityClass);
    this.tag = tag;
  }

  @Override
  protected Bundle getBundle(View view) {
    Bundle bundle = new Bundle();
    bundle.putLong(tag, (Long) view.getTag(view.getId()));
    return bundle;
  }
}
