package com.sarality.app.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.common.collect.Maps;
import com.sarality.app.common.collect.Sets;
import com.sarality.app.loader.DataConsumer;
import com.sarality.app.loader.DataLoader;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.datasource.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Base implementations for all classes that initialize Views.
 * <p/>
 * The constructor takes in a {@code FragmentActivity} so that the DataSource can be loaded asynchronously using
 * a {@code Loader}.
 *
 * @param <V> Type of view being initialized.
 * @param <T> Type of data needed to initialize the view.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseViewInitializer<V extends View, T> implements ViewInitializer<V, T>, DataConsumer<T> {

  private static final Logger logger = LoggerFactory.getLogger(BaseViewInitializer.class);

  private static final Set<TriggerType> DEFAULT_SUPPORTED_TRIGGER_TYPES = Sets.of(TriggerType.CLICK,
      TriggerType.LONG_CLICK);

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
  public void consume(T data) {
    init(data);
  }

  @Override
  public void init(T data) {
    render(data);
    setupActions();
  }

  @Override
  public void init(DataSource<T> dataSource) {
    activity.getSupportLoaderManager().initLoader(0, null, new DataLoader<T>(activity, dataSource, this));
  }

  protected boolean assertValidAction(ViewAction action) {
    // Check if the view exists
    int viewId = action.getViewId();
    View actionView = view.findViewById(viewId);
    if (actionView == null) {
      String viewIdName = activity.getResources().getResourceEntryName(viewId);
      throw new IllegalArgumentException("View with id " + viewIdName + " not found while registering action "
          + action.getClass().getSimpleName());
    }

    // Check if the trigger type supported on the view
    TriggerType triggerType = action.getTriggerType();
    if (!getSupportedTriggerTypes().contains(triggerType)) {
      String viewIdName = activity.getResources().getResourceName(viewId);
      throw new IllegalArgumentException("Cannot register a " + triggerType + " actions for the view with id "
          + viewIdName + ". Only the following triggers are supported on the view " + getSupportedTriggerTypes());
    }

    // Check if an action with the trigger type already exists on the view
    if (actionMap.get(viewId).get(triggerType) != null) {
      String viewIdName = activity.getResources().getResourceName(viewId);
      throw new IllegalArgumentException("Cannot register multiple " + triggerType + " actions for the view with id "
          + viewIdName);
    }

    return true;
  }

  protected Set<TriggerType> getSupportedTriggerTypes() {
    return DEFAULT_SUPPORTED_TRIGGER_TYPES;
  };

  @Override
  public void registerAction(ViewAction action) {
    // First create a Map for Actions associated with the ViewId.
    int viewId = action.getViewId();
    if (!actionMap.containsKey(viewId)) {
      actionMap.put(viewId, Maps.<TriggerType, ViewAction>empty());
    }

    assertValidAction(action);

    actionList.add(action);
    actionMap.get(viewId).put(action.getTriggerType(), action);
  }

  protected List<ViewAction> getRegisteredActions() {
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
  public void setupActions() {
    for (ViewAction action : getRegisteredActions()) {
      int actionViewId = action.getViewId();
      View actionView = view.findViewById(actionViewId);
      action.setupAction(actionView);
    }
  }
}
