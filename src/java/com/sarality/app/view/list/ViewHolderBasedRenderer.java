package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.BaseViewRenderer;

/**
 * Base implementation for ViewHolder pattern based View Renderer
 * <p/>
 * A ViewHolder is an optimization that keeps reference to elements inside a view. Since row views are reused, keeping
 * a reference around saves calls to {@link View#findViewById(int)} which can be a very expensive call.
 *
 * @param <H> Type of ViewHolder
 * @param <T> Type of data for the
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class ViewHolderBasedRenderer<H, T> extends BaseViewRenderer<View, T> {

  /**
   * Create a View Holder for the given view
   *
   * @param view The View to create a ViewHolder for.
   * @return ViewHolder created for the given view.
   */
  public abstract H createViewHolder(View view);

  /**
   * Render the View using the ViewHolder.
   *
   * @param view View to render.
   * @param viewHolder ViewHolder for the View to be rendered.
   * @param data Data used to render the view.
   */
  public abstract void renderView(View view, H viewHolder, T data);

  @Override
  public void renderView(View view, T data) {
    @SuppressWarnings("unchecked")
    H viewHolder = (H) view.getTag();
    if (viewHolder == null) {
      viewHolder = createViewHolder(view);
      view.setTag(viewHolder);
    }
    renderView(view, viewHolder, data);
  }
}
