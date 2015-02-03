package com.sarality.app.view.action;

/**
 * Interface for all actions that can be performed in an app.
 *
 * @param <C> Type of context needed to perform the action. Contains information like view triggering the view.
 * @author abhideep@ (Abhideep Singh)
 */
public interface Action<C> {

  /**
   * Perform the action with the given context.
   *
   * @param actionContext Context needed to perform the action.
   */
  public void performAction(C actionContext);
}
