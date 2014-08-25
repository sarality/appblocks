package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.action.ComponentActionManager;

/**
 * Interface for classes that render a row in a List View.
 * 
 * @author abhideep@dothat.in (Abhideep Singh)
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
   * The Cache makes it easier to access the elements of the View rather than having to do a lookup by Id each time.
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
  public void setupActions(View rowView, ListRowViewCache rowViewCache, T value, ComponentActionManager actionManager);

  /**
   * Adds to the list of tags that will be set on a particular view from activity other than current activity
   * 
   * @param viewId
   * @param value
   */
  public void setAdditionalTagList(int viewId, Object data, T value);

 /**
   * Gets the Animation resource to be applied to the the row
   * @param rowView
   * @param rowValue
   * @return Returns the animation object associated with the view
   */
  public int getAnimation(View rowView, T rowValue);
}
