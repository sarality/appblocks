package com.sarality.app.view.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Starts new activity when a class when an item is clicked 
 * 
 *
 */
public class StartActivityAction extends BaseViewAction {
	
	//The context of the class that is calling the action
	private final Context context;
	
	//The class that has to be opened.
	private final Class<? extends Activity> newActivityClass;
	
	/**
	   * Constructor.
	   * 
	   * @param viewId
	   *          Id of view that triggers the action.
	   * @param triggerType
	   *          Type of event that triggers the action.
	   * @param context
	   *          Context of the class that is triggers the action.
	   * @param Class
	   *          Class that has to be opened.
	   */
	public StartActivityAction(int viewId, TriggerType triggerType, Context context, 
			Class<? extends Activity> newActivityClass) {
		super(viewId, triggerType);
		this.context = context;
		this.newActivityClass = newActivityClass;
	}

	@Override
	public void prepareView(View view, Object value) {
		
	}

	@Override
	public ViewAction cloneInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doAction(View view, ViewActionTrigger actionDetail,
			ViewDetail viewDetail) {
		Intent intent = new Intent(context, newActivityClass);
		context.startActivity(intent);
		return true;
	}}
