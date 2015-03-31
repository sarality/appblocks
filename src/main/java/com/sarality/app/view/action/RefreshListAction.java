package com.sarality.app.view.action;

import android.view.View;

import com.sarality.app.view.list.ListComponent;

/**
 * Action that would refresh the ListComponent and re-render
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class RefreshListAction<T> extends BaseViewAction {

  private final ListComponent<T> component;

  /**
   * Constructor.
   * 
   * @param viewId
   *          Id of the View that triggers the action.
   * @param triggerType
   *          Type of event that triggers the action.
   * @param component
   *          Reference to the list component that would need to be refreshed          
   */
  public RefreshListAction(int viewId, TriggerType triggerType, ListComponent<T> component) {
    super(viewId, triggerType);
    this.component = component;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger trigger, ViewDetail viewDetail) {
    component.refresh();
    return true;
  }
}
