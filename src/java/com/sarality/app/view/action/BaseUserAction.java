package com.sarality.app.view.action;


public abstract class BaseUserAction implements ViewAction {
  private final int viewId;
  private final InputEvent event;
  
  public BaseUserAction(int viewId, InputEvent event) {
    this.viewId = viewId;
    this.event = event;
  }

  @Override
  public int getViewId() {
    return viewId;
  }

  @Override
  public final InputEvent getEvent() {
    return event;
  }
}
