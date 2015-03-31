package com.sarality.app.view.action;

import android.content.Context;
import android.view.View;

import com.sarality.app.common.collect.Maps;

import java.util.Map;

/**
 * Class that manages the registration and setup of actions on a View.
 * <p/>
 * Used in conjunction with Initializers.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewActionManager {

  private final Context context;
  private final Map<Integer, AppViewAction> onClickActionMap = Maps.empty();
  private final Map<Integer, AppViewAction> onLongClickActionMap = Maps.empty();
  private final Map<Integer, AppViewAction> onTouchActionMap = Maps.empty();

  public ViewActionManager(Context context) {
    this.context = context;
  }

  public void setOnClickAction(int viewId, View.OnClickListener action) {
    setOnClickAction(viewId, new ClickListenerAction(action));
  }

  public void setOnTouchAction(int viewId, View.OnTouchListener action) {
    setOnTouchAction(viewId, new ViewActionManager.TouchListenerAction(action));
  }

  public void setOnLongClickAction(int viewId, View.OnLongClickListener action) {
    setOnLongClickAction(viewId, new ViewActionManager.LongClickListenerAction(action));
  }

  public void setOnClickAction(int viewId, AppViewAction action) {
    AppViewAction currentAction = onClickActionMap.get(viewId);
    if (currentAction != null) {
      String viewName = context.getResources().getResourceName(viewId);
      throw new IllegalStateException("Trying to register multiple Actions for a Click event on View with id " +
          viewName + "Use action chaining instead by all calling registerSuccessAction instead");
    }
    onClickActionMap.put(viewId, action);
  }

  public void setOnLongClickAction(int viewId, AppViewAction action) {
    AppViewAction currentAction = onClickActionMap.get(viewId);
    if (currentAction != null) {
      String viewName = context.getResources().getResourceName(viewId);
      throw new IllegalStateException("Trying to register multiple Actions for a Click event on View with id " +
          viewName + "Use action chaining instead by all calling registerSuccessAction instead");
    }
    onClickActionMap.put(viewId, action);
  }

  public void setOnTouchAction(int viewId, AppViewAction action) {
    AppViewAction currentAction = onTouchActionMap.get(viewId);
    if (currentAction != null) {
      String viewName = getContext().getResources().getResourceName(viewId);
      throw new IllegalStateException("Trying to register multiple Actions for a Touch event on View with id " +
          viewName + "Use action chaining instead by all calling registerSuccessAction instead");
    }
    onTouchActionMap.put(viewId, action);
  }

  public void setup(View view) {
    // Setup all OnClick Actions
    for (int viewId : onClickActionMap.keySet()) {
      View actionView = view.findViewById(viewId);
      if (actionView == null) {
        String viewName = getContext().getResources().getResourceName(viewId);
        throw new IllegalStateException("Cannot setup an action for a Click event on View with id " +
            viewName + ". View with given id not found in the given parent View");
      }
      actionView.setOnClickListener(onClickActionMap.get(viewId));
    }

    // Setup all OnLongClick Actions
    for (int viewId : onLongClickActionMap.keySet()) {
      View actionView = view.findViewById(viewId);
      if (actionView == null) {
        String viewName = getContext().getResources().getResourceName(viewId);
        throw new IllegalStateException("Cannot setup an action for a Long Click event on View with id " +
            viewName + ". View with given id not found in the given parent View");
      }
      actionView.setOnLongClickListener(onLongClickActionMap.get(viewId));
    }

    // Setup all OnTouch Actions
    for (int viewId : onTouchActionMap.keySet()) {
      View actionView = view.findViewById(viewId);
      if (actionView == null) {
        String viewName = getContext().getResources().getResourceName(viewId);
        throw new IllegalStateException("Cannot setup an action for a Touch event on View with id " +
            viewName + ". View with given id not found in the given parent View");
      }
      actionView.setOnTouchListener(onTouchActionMap.get(viewId));
    }
  }

  protected Context getContext() {
    return context;
  }

  /**
   * ViewAction that wraps a {@code OnClickListener}.
   */
  private class ClickListenerAction extends AppViewAction {

    private final View.OnClickListener listener;

    private ClickListenerAction(View.OnClickListener listener) {
      this.listener = listener;
    }

    @Override
    protected boolean doAction() {
      if (listener != null) {
        listener.onClick(getActionContext().getView());
      }
      return true;
    }
  }

  /**
   * ViewAction that wraps a {@code OnLongClickListener}.
   */
  private class LongClickListenerAction extends AppViewAction {

    private final View.OnLongClickListener listener;

    private LongClickListenerAction(View.OnLongClickListener listener) {
      this.listener = listener;
    }

    @Override
    protected boolean doAction() {
      if (listener != null) {
        listener.onLongClick(getActionContext().getView());
      }
      return true;
    }
  }

  /**
   * ViewAction that wraps a {@code OnTouchListener}.
   */
  private class TouchListenerAction extends AppViewAction {

    private final View.OnTouchListener listener;

    private TouchListenerAction(View.OnTouchListener listener) {
      this.listener = listener;
    }

    @Override
    protected boolean doAction() {
      if (listener != null) {
        listener.onTouch(getActionContext().getView(), getActionContext().getMotionEvent());
      }
      return true;
    }
  }

}
