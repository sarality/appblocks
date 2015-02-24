package com.sarality.app.view.list;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sarality.app.view.ViewRenderer;

/**
 * Utility class that manages the rendering of the View to be displayed when the ListView is empty.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class EmptyListViewRenderer<T> {
  private final View view;
  private final ViewRenderer<View, T> renderer;
  private final T data;

  EmptyListViewRenderer(View view, ViewRenderer<View, T> renderer, T data) {
    this.view = view;
    this.renderer = renderer;
    this.data = data;
  }

  void render(ListView listView) {
    View emptyView = getViewToRender();
    // Need to set the empty view only if the view is not null.
    if (emptyView != null) {
      listView.setEmptyView(view);
    }
  }

  void render(LinearLayout layout, boolean isListEmpty) {
    View emptyView = getViewToRender();

    // Need to set the empty view only if the view is not null.
    if (emptyView != null) {
      if (!isListEmpty) {
        emptyView.setVisibility(View.GONE);
      } else {
        emptyView.setVisibility(View.VISIBLE);
      }
    }
  }

  private View getViewToRender() {
    // If no renderer is provided, return the empty view as is, otherwise initialize the empty view.
    if (view != null && renderer != null) {
      renderer.render(view, data);
    }
    return view;
  }
}
