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
   * @param value Data used to generate the row
   * @return Resource Id for the row layout
   */
  public int getRowLayout(T value);

}
