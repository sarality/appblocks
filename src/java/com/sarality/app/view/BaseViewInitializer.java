package com.sarality.app.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.common.collect.Maps;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.datasource.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Base implementations for all classes that initialize Views.
 * <p/>
 * The constructor takes in a {@code FragmentActivity} so that the DataSource can be loaded asynchronously using
 * a {@code Loader}.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseViewInitializer<V extends View, T> implements ViewInitializer<V, T> {

  private static final Logger logger = LoggerFactory.getLogger(BaseViewInitializer.class);

  private final FragmentActivity activity;
  private final V view;
  private final Map<Integer, Map<TriggerType, ViewAction>> actionMap = Maps.empty();
  private final List<ViewAction> actionList = Lists.of();

  public BaseViewInitializer(FragmentActivity activity, V view) {
    this.activity = activity;
    this.view = view;
  }

  @Override
  public V getView() {
    return view;
  }

  @Override
  public void init(T data) {
    // By default the initialization happens synchronously. Other classes can override this method to perform actions
    // like loading of images and resources asynchronously in the init method.
    render(data);
  }

  @Override
  public void init(DataSource<T> dataSource) {
    activity.getSupportLoaderManager().initLoader(0, null, new ViewDataLoader<T>(activity, dataSource, this));
  }

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

  @Override
  public List<ViewAction> getRegisteredActions() {
    return actionList;
  }

  /**
   * @return Context for the Initializer.
   */
  protected Context getContext() {
    return activity;
  }

  /**
   * Setup actions registered for the view.
   */
  protected void setupActions() {
    for (ViewAction action : getRegisteredActions()) {
      int actionViewId = action.getViewId();
      View actionView = view.findViewById(actionViewId);
      if (actionView != null) {
        action.setupAction(actionView);
      } else {
        String viewName = activity.getResources().getResourceEntryName(actionViewId);
        logger.warn("View with name " + viewName + " not found while registering action "
            + action.getClass().getSimpleName());
      }
    }
  }
}
