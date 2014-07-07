package com.sarality.app.view.dialog;

import java.util.List;

import android.content.Context;
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
  public int getLayout();

  /**
   * Render the given Dialog.
   * 
   * @param View associated with the Dialog.
   * @param value Data used to select items to be displayed on the dialog.
   */
  public View render(Context context, Object value);

  /**
   * Setup the Actions and corresponding Listeners on the Dialog.
   * 
   * @param View associated with the dialog.
   * @param Object Data used to generate the dialog.
   */
  public void setupActions(View view, Object object);

  /**
   * Set up the action List that need to be performed by the views on this Dialog
   * 
   * @param actionList
   */
  public void setActionList(List<ViewAction<T>> actionList);

  /**
   * The dialog layout
   * 
   * @return
   */
  public int getDialogLayout();

  /**
   * The list view within the dialog
   * 
   * @return
   */
  public int getListView();

  /**
   * The textview within the list
   * 
   * @return
   */
  int getTextView();
}
