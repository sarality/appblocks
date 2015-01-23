package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.action.ViewAction;

import java.util.List;

/**
 * Interface for Rendering of a ListView row for the given data.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ListViewRowRenderer<T> {

  /**
   * Returns the id of the Layout to be used for the row.
   *
   * @param value Data used to generate the row
   * @return Resource Id for the row layout
   */
  public int getRowLayout(T value);


  /**
   * Render the List Row Item for the given data.
   *
   * @param view View for the List Row Item.
   * @param data Data for the List Row Item.
   */
  public void render(View view, T data);

  /**
   * Registers the given View Action for initialization.
   * <p/>
   * It is the responsibility of the implementation to setup these actions in {@code #init} method.
   *
   * @param action Action to be setup for the given view.
   */
  public void registerAction(ViewAction action);

  /**
   * Returns list of actions that need to be setup on the view.
   *
   * @return List of actions registered with the initializer.
   */
  public List<ViewAction> getRegisteredActions();

}
