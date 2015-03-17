package com.sarality.app.view.action;

/**
 * Extracts the context for an action given an existing context.
 * <p/>
 * Used to perform actions on different types View and objects using the same trigger event.
 *
 * @param <C> Type of existing ActionContext context.
 * @param <D> Type of extracted ActionContext context.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ActionContextExtractor<C, D> {

  /**
   * Extract Context for the given context.
   *
   * @param context Existing context.
   * @return Extracted Context
   */
  public D extractContext(C context);
}
