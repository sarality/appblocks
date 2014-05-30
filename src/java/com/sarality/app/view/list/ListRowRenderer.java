package com.sarality.app.view.list;

import java.util.List;

import com.sarality.app.view.action.ViewAction;

import android.view.View;

/**
 * Interface for classes that render a row in a List View.
 * 
 * @author abhideep@dothat.in (Abhideep Singh)
 *
 * @param <T> The type of data that is used to render a row.
 */
public interface ListRowRenderer<T> {

  /**
   * Returns the id of the Layout to be used for the row.
   * 
   * @param value Data used to generate the row
   * @return Integer Id for the row layout
   */
  public int getRowLayout(T value);

  /**
   * Populate the Cache for ListView Row.
   * <p>
   * The Cache makes it easier to access the elements of the View rather than
   * having to do a lookup by Id each time.
   * 
   * @param view
   * @return
   */
  public void populateViewCache(View rowView, ListRowViewCache rowViewCache, T value);

  /**
   * Render the given ListView row.
   * 
   * @param rowView View associated with the row.
   * @param rowViewCache Cache of the elements inside the View for the row.
   * @param value Data used to generate the row.
   */
  public void render(View rowView, ListRowViewCache rowViewCache, T value);

  /**
   * Setup the Actions and corresponding Listeners on the List View row.
   * 
   * @param rowView View associated with the row.
   * @param value Data used to generate the row.
   * @param actionList List of Actions to be setup on the row and it's elements.
   */
  public void setupActions(View rowView, T value, List<ViewAction> actionList);
}
