package com.sarality.app.view.action;

import com.sarality.app.common.collect.Lists;

import java.util.List;

/**
 * Base implementation of Action.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseAction<A extends BaseAction<A>> implements Action<A> {

  private final List<A> successActionList = Lists.emptyList();
  private final List<A> failureActionList = Lists.emptyList();

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
  public final void registerSuccessAction(A action) {
    successActionList.add(action);
  }

  @Override
  public final void registerFailureAction(A action) {
    failureActionList.add(action);
  }

  protected abstract A getInstance();

  protected abstract boolean doAction();

  private boolean doOnSuccess() {
    boolean success;
    for (A action : successActionList) {
      action.setContext(getInstance());
      success = action.perform();
      if (!success) {
        return false;
      }
    }
    return true;
  }

  private boolean doOnFailure() {
    boolean success;
    for (A action : failureActionList) {
      action.setContext(getInstance());
      success = action.perform();
      if (!success) {
        return false;
      }
    }
    return true;
  }

}
