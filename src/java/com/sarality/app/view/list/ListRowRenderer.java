package com.sarality.app.view.list;

import android.view.View;

/**
 * 
 * @author abhideep@dothat.in (Abhideep Singh)
 *
 * @param <T>
 * @param <H>
 */
public interface ListRowRenderer<T> {

  /**
   * @param view
   * @return
   */
  public int getRowLayout(T value);

  /**
   * 
   * @param view
   * @return
   */
  public void populateViewCache(View rowView, ListRowViewCache rowViewCache, T value);

  /**
   * 
   * @param view
   * @param value
   */
  public void render(View rowView, ListRowViewCache rowViewCache, T value);
  
  /**
   * 
   * @param rowView
   */
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value);
}
