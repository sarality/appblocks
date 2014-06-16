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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((trigger == null) ? 0 : trigger.hashCode());
    result = prime * result + viewId;
    return result;
  }

  
  private boolean equals(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof ViewTrigger) {
      ViewTrigger viewTrigger = (ViewTrigger) obj;
      return equals(viewId, viewTrigger.getViewId()) && equals(trigger, viewTrigger.getTriggerType());
    }
    return false;
  }
}
