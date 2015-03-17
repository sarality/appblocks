package com.sarality.app.view.action;

/**
 * Utility class to perform a chained action.
 * <p/>
 * A chained action is an action performed on the successful completion or failure of an action.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ActionPerformer<C, D> {

  private final Action<C> triggeringAction;
  private final Action<D> triggeredAction;
  private final ActionContextExtractor<C, D> contextSetter;

  ActionPerformer(Action<C> triggeringAction, Action<D> triggeredAction, ActionContextExtractor<C, D> contextSetter) {
    this.triggeringAction = triggeringAction;
    this.triggeredAction = triggeredAction;
    this.contextSetter = contextSetter;
  }

  boolean perform() {
    C context = triggeringAction.getActionContext();
    D newContext = contextSetter.extractContext(context);
    triggeredAction.setActionContext(newContext);
    return triggeredAction.perform();
  }
}
