package com.sarality.app.view;

import android.view.View;

/**
 * Basic View Renderer to render any kind of view
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public interface ViewRenderer<T> {
  public void render(View view, T data);
}
