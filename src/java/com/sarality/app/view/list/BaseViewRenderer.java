package com.sarality.app.view.list;

import android.content.Context;
import android.view.View;

/**
 * Base View Renderer to render any kind of view
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public abstract class BaseViewRenderer<T> implements ViewRenderer<T> {
  protected final Context context;
  protected final View view;

  public BaseViewRenderer(Context context, View view) {
    this.context = context;
    this.view = view;
  }

}
