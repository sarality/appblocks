package com.sarality.app.view.list;

import android.view.View;

public class CompositeListRowRenderer<T> implements ListRowRenderer<T> {

  private final ListRowRendererSelector<T> selector;
  
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
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value) {
    ListRowRenderer<T> renderer = selector.select(value);
    renderer.setupActions(rowView, rowViewCache, value);
  }
}
