package com.sarality.app.view.action;

/**
 * Interface for all actions that can be performed in an app.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface Action<A extends Action<A>> {

  /**
   * Perform the action.
   *
   * @return {@code true} if the action was performed successfully, {@code false} otherwise.
   */
  public boolean perform();

  /**
   * Set the context for the execution of the current action.
   *
   * @param action Action that was triggered that then resulted in the current action being called.
   */
  public void setContext(A action);

  /**
   * Register the action that needs to be executed when this action completes successfully.
   *
   * @param action Actions to be performed on success.
   */
  public void registerSuccessAction(A action);

  /**
   * Register the action that needs to be executed when this action fails.
   *
   * @param action Actions to be performed on failure.
   */
  public void registerFailureAction(A action);

}
