package com.sarality.app.view.fragment.action;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Replaced the old fragment with the new fragment in a view container of an activity
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ReplaceFragmentAction extends BaseViewAction {

  // New Fragment
  private final Fragment fragment;
  // Current fragment that will be replaced
  private final Fragment oldFragment;
  // The view container of the fragment
  private final int fragmentContainer;

  //Add to backStack ?
  private final boolean addToBackStack;

  /**
   * Constructor.
   *
   * @param viewId Id of the View that triggered the action.
   * @param triggerType The type of trigger.
   * @param oldFragment Old Fragment that will be replaced
   * @param fragment New Fragment
   * @param fragmentContainer View container of the fragments.
   */
  public ReplaceFragmentAction(int viewId, TriggerType triggerType, Fragment oldFragment, Fragment fragment,
                               int fragmentContainer, boolean addToBackStack) {
    super(viewId, triggerType);
    this.fragment = fragment;
    this.oldFragment = oldFragment;
    this.fragmentContainer = fragmentContainer;
    this.addToBackStack = addToBackStack;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    FragmentManager fragmentManager = oldFragment.getActivity().getSupportFragmentManager();
    if(addToBackStack){
      fragmentManager.beginTransaction().replace(fragmentContainer, fragment).addToBackStack(null).commit();
    }
    else{
      fragmentManager.beginTransaction().replace(fragmentContainer, fragment).commit();
    }
    return false;
  }
}
