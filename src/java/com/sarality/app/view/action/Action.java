package com.sarality.app.view.action;

/**
 * Interface for all actions that can be performed in an app.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface Action<C> {

  /**
   * Perform the action.
   *
   * @return {@code true} if the action was performed successfully, {@code false} otherwise.
   */
  public boolean perform();

  /**
   * Set the context for the execution of the current action.
   *
   * @param context Context that triggered this action.
   */
  public void setActionContext(C context);

  /**
   * Get the context for the execution of the current action.
   *
   * @return Context associated with the Action
   */
  public C getActionContext();

  /**
   * Register the action that needs to be executed when this action completes successfully.
   *
   * @param action Actions to be performed on success.
   */
  public <D> void registerSuccessAction(Action<D> action, ActionContextExtractor<C, D> contextSetter);

  /**
   * Register the action that needs to be executed when this action fails.
   *
   * @param action Actions to be performed on failure.
   */
  public <D> void registerFailureAction(Action<D> action, ActionContextExtractor<C, D> contextSetter);

}
