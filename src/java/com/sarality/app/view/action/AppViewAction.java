package com.sarality.app.view.action;

import android.view.MotionEvent;
import android.view.View;


/**
 * Base implementation for Actions on a View
 *
 * TODO(abhideep): Rename to ViewAction once the other one is deprecated.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class AppViewAction extends BaseAction<AppViewAction> implements View.OnClickListener, View.OnLongClickListener,
    View.OnTouchListener {

  private View view;
  private MotionEvent motionEvent;

  @Override
  public final void setContext(AppViewAction action) {
    setView(action.getView());
    setMotionEvent(action.getMotionEvent());
  }

  @Override
  public final void onClick(View clickedView) {
    setView(clickedView);
    perform();
  }

  @Override
  public final boolean onLongClick(View longClickedView) {
    setView(longClickedView);
    return perform();
  }

  @Override
  public final boolean onTouch(View touchedEvent, MotionEvent touchEvent) {
    setView(touchedEvent);
    setMotionEvent(touchEvent);
    return perform();
  }

  @Override
  protected final AppViewAction getInstance() {
    return this;
  }

  public final void setView(View view) {
    this.view = view;
  }

  protected final View getView() {
    return view;
  }

  protected final MotionEvent getMotionEvent() {
    return motionEvent;
  }

  protected final void setMotionEvent(MotionEvent motionEvent) {
    this.motionEvent = motionEvent;
  }
}
