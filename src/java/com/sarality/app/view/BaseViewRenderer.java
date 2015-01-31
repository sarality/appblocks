package com.sarality.app.view;

import android.view.View;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.common.collect.Maps;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Base implementations for all classes that renders a View.
 *
 * @param <V> Type of view being rendered.
 * @param <T> Type of data needed to render the view.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseViewRenderer<V extends View, T> implements ViewRenderer<V, T> {

  private static final Logger logger = LoggerFactory.getLogger(BaseViewRenderer.class);

  private final Map<Integer, Map<TriggerType, ViewAction>> actionMap = Maps.empty();
  private final List<ViewAction> actionList = Lists.of();

  @Override
  public void render(V view, T data) {
    renderView(view, data);
    setupActions(view);
  }

  public abstract void renderView(V view, T data);

  @Override
  public void registerAction(ViewAction action) {
    int viewId = action.getViewId();
    if (!actionMap.containsKey(viewId)) {
      actionMap.put(viewId, Maps.<TriggerType, ViewAction>empty());
    }
    TriggerType triggerType = action.getTriggerType();
    ViewAction existingAction = actionMap.get(viewId).get(triggerType);
    if (existingAction != null) {
      actionList.remove(existingAction);
    }
    actionList.add(action);
    actionMap.get(viewId).put(triggerType, action);
  }

  /**
   * Setup actions registered for the view.
   */
  public void setupActions(V view) {
    for (ViewAction action : actionList) {
      int actionViewId = action.getViewId();
      View actionView = view.findViewById(actionViewId);
      if (actionView != null) {
        action.setupAction(actionView);
      } else {
        String viewName = view.getContext().getResources().getResourceEntryName(actionViewId);
        logger.warn("View with name " + viewName + " not found while setting up action "
            + action.getClass().getSimpleName());
      }
    }
  }
}
