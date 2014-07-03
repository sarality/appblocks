package com.sarality.app.view.dialog;

import java.util.List;

import android.view.View;

import com.sarality.app.view.action.ViewAction;

/**
 * Interface for classes that render a Dialog.
 * 
 * @author sunayna@dothat.in(Sunayna Uberoy)
 * 
 * @param <T> The type of data that is used to render a dialog.
 */
public interface DialogRenderer<T> {

  /**
   * Returns the id of the Layout to be used for the dialog.
   * 
   * @param value Data used to generate the dialog
   * @return Integer Id for the layout
   */
  public int getLayout(T value);

  /**
   * Render the given Dialog.
   * 
   * @param View associated with the Dialog.
   * @param value Data used to select items to be displayed on the dialog.
   */
  public void render(View view, T value);

  /**
   * Setup the Actions and corresponding Listeners on the Dialog.
   * 
   * @param View associated with the dialog.
   * @param value Data used to generate the dialog.
   * @param actionList List of Actions to be setup on the dialog and it's elements.
   */
  public void setupActions(View view, List<ViewAction<T>> actionList, T value);
}
