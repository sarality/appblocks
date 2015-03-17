package com.sarality.app.view;

import android.view.View;

import com.sarality.app.view.action.ViewAction;

/**
 * Basic View Renderer to render any kind of view
 *
 * @param <V> Type of View to be rendered.
 * @param <T> Type of data used to render the view.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ViewRenderer<V extends View, T> {

  /**
   * Render the View based on the given data.
   *
   * @param view The View to render.
   * @param data The data used to render the view.
   */
  public void render(V view, T data);

  /**
   * Registers the given View Action for initialization.
   * <p/>
   * It is the responsibility of the implementation to setup these actions in the {@code #render} method.
   *
   * @param action Action to be setup for the given view.
   */
  public void registerAction(ViewAction action);

  /**
   * Setup action on the view.
   *
   * @param view View that this is a renderer for.
   */
  public void setupActions(V view);
}
