package com.sarality.app.view.fragment.action;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Hides the given fragment and then displays the fragment with the given id.
 * <p>
 * Also adds the fragment being hidden to the back stack.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class SwitchFragmentsAction extends BaseViewAction {

  // Current fragment that will be hidden. Also the fragment that the action is being performed on.
  private final Fragment fragment;
  // The id of the fragment to display.
  private final int showFragmentId;

  /**
   * Constructor.
   * 
   * @param viewId Id of the View that triggered the action.
   * @param triggerType The type of trigger.
   * @param fragment Fragment that the trigger occurs on. Also the fragment that will be hidden.
   * @param showFragmentId Id of the fragment that needs to be displayed.
   */
  public SwitchFragmentsAction(int viewId, TriggerType triggerType, Fragment fragment, int showFragmentId) {
    super(viewId, triggerType);
    this.fragment = fragment;
    this.showFragmentId = showFragmentId;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    FragmentManager fragmentManager = fragment.getActivity().getSupportFragmentManager();
    Fragment showFragment = fragmentManager.findFragmentById(showFragmentId);

    fragmentManager.beginTransaction().show(showFragment).hide(fragment).addToBackStack(null).commit();
    return false;
  }
}
