package com.sarality.app.view.action;

import com.sarality.app.common.collect.Lists;

import java.util.List;

/**
 * Base implementation of Action.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseAction<C> implements Action<C> {

  private C context;
  private final List<ActionPerformer<C, ?>> successActionList = Lists.emptyList();
  private final List<ActionPerformer<C, ?>> failureActionList = Lists.emptyList();

  @Override
  public C getActionContext() {
    return context;
  }

  @Override
  public void setActionContext(C context) {
    this.context = context;
  }

  @Override
  public final boolean perform() {
    try {
      boolean success = doAction();
      if (success) {
        return doOnSuccess();
      } else {
        doOnFailure();
        return false;
      }
    } catch (Throwable throwable) {
      doOnFailure();
      return false;
    }
  }


  @Override
  public <D> void registerSuccessAction(Action<D> action, ActionContextExtractor<C, D> contextSetter) {
    successActionList.add(new ActionPerformer<C, D>(this, action, contextSetter));
  }


  @Override
  public <D> void registerFailureAction(Action<D> action, ActionContextExtractor<C, D> contextSetter) {
    failureActionList.add(new ActionPerformer<C, D>(this, action, contextSetter));
  }

  protected abstract boolean doAction();

  private boolean doOnSuccess() {
    boolean success;
    for (ActionPerformer<C, ?> action : successActionList) {
     success = action.perform();
      if (!success) {
        return false;
      }
    }
    return true;
  }

  private boolean doOnFailure() {
    boolean success;
    for (ActionPerformer<C, ?> action : failureActionList) {
      success = action.perform();
      if (!success) {
        return false;
      }
    }
    return true;
  }

}
