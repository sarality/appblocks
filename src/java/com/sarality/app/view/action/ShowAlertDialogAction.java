package com.sarality.app.view.action;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.sarality.app.view.ViewRenderer;

/**
 * Action that would show the Alert Dialog with a message
 *
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ShowAlertDialogAction<T> extends BaseViewAction implements ViewAction {
  private final Context context;
  private final int layout;
  private final ViewRenderer<T> renderer;

  /**
   * Constructor.
   *
   * @param viewId Id of the View that triggers the action.
   * @param triggerType Type of event that triggers the action.
   */
  public ShowAlertDialogAction(int viewId, TriggerType triggerType, Context context, int layout,
                               ViewRenderer<T> renderer) {
    super(viewId, triggerType);
    this.context = context;
    this.layout = layout;
    this.renderer = renderer;
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

    LayoutInflater factory = LayoutInflater.from(context);
    final View dialogView = factory.inflate(layout, null);

    T data = (T) view.getTag(view.getId());
    renderer.render(dialogView, data);
    alertDialogBuilder.setView(dialogView).create().show();
    return true;
  }
}
