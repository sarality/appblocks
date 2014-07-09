package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.action.ComponentActionManager;

/**
 * Renders a ListView where each of the row is rendered differently.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of Object being rendered in the ListView.
 */
public class CompositeListRowRenderer<T> implements ListRowRenderer<T> {

  // Used to select the type of Renderer for each row.
  private final ListRowRendererSelector<T> selector;
  
  /**
   * Constructor.
   * 
   * @param selector Class used to select which Renderer to use for each row.
   */
  public CompositeListRowRenderer(ListRowRendererSelector<T> selector) {
    this.selector = selector;
  }

  @Override
  public int getRowLayout(T value) {
    ListRowRenderer<T> renderer = selector.select(value);
    return renderer.getRowLayout(value);
  }

  @Override
  public void populateViewCache(View rowView, ListRowViewCache rowViewCache, T value) {
    ListRowRenderer<T> renderer = selector.select(value);
    renderer.populateViewCache(rowView, rowViewCache, value);    
  }

  @Override
  public void render(View rowView, ListRowViewCache rowViewCache, T value) {
    ListRowRenderer<T> renderer = selector.select(value);
    renderer.render(rowView, rowViewCache, value);    
  }

  @Override
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value, ComponentActionManager componentManager) {
    ListRowRenderer<T> renderer = selector.select(value);
    renderer.setupActions(rowView, rowViewCache, value, componentManager);
  }

  @Override
  public void setTagList(int viewId, Object value){
    // Do nothing
  }
}
