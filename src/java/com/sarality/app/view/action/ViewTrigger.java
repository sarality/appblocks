package com.sarality.app.view.action;

/**
 * Creates a View Id - Trigger pair
 * 
 * @author sunayna@dothat.in sunayna
 * 
 */
public class ViewTrigger {
  private final int viewId;
  private final TriggerType trigger;

  /**
   * Constructor
   * 
   * @param viewId
   *          View ID
   * @param trigger
   *          Corresponding Trigger
   */
  public ViewTrigger(int viewId, TriggerType trigger) {
    this.viewId = viewId;
    this.trigger = trigger;
  }

  /**
   * Gets the View ID
   * 
   * @return view Id
   */
  public int getViewId() {
    return viewId;
  }

  /**
   * Gets the trigger Type
   * 
   * @return trigger type
   */
  public TriggerType getTriggerType() {
    return trigger;
  }
}
