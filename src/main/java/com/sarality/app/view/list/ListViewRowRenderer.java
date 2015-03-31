package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.ViewRenderer;
import com.sarality.app.view.action.ViewAction;

import java.util.List;

/**
 * Interface for Rendering of a ListView row for the given data.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ListViewRowRenderer<T> extends ViewRenderer<View, T> {

  /**
   * Returns the id of the Layout to be used for the row.
   *
   * @param position Index of the row being rendered
   * @param value Data used to generate the row
   * @return Resource Id for the row layout
   */
  public int getRowLayout(int position, T value);

  /**
   * @return Number of types of View rendered by the Renderer.
   */
  public int getViewTypeCount();


  /**
   * Returns the type of row being rendered for the given position and data.
   *
   * @param position Index of the row being rendered
   * @param value Data used to generate the row
   * @return integer type for the row.
   */
  public int getViewType(int position, T value);
}
