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
public abstract class AppViewAction extends BaseAction<AppViewActionContext>
    implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

  @Override
  public final void onClick(View clickedView) {
    setActionContext(new AppViewActionContext(clickedView));
    perform();
  }

  @Override
  public final boolean onLongClick(View longClickedView) {
    setActionContext(new AppViewActionContext(longClickedView));
    return perform();
  }

  @Override
  public final boolean onTouch(View touchedView, MotionEvent touchEvent) {
    setActionContext(new AppViewActionContext(touchedView, touchEvent));
    return perform();
  }
}
