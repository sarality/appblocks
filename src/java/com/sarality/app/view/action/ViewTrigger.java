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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ViewTrigger other = (ViewTrigger) obj;
    if (trigger != other.trigger)
      return false;
    if (viewId != other.viewId)
      return false;
    return true;
  }
}
