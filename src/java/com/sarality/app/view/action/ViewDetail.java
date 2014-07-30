package com.sarality.app.view.action;

import android.view.View;

/**
 * Details about the view that triggered the event.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class ViewDetail {

  // View that triggered the event.
  private View view;
  // Parent of the view that triggered the event, if provided by Android.
  private View parentView;

  /**
   * Constructor.
   * 
   * @param view
   * @param parentView
   */
  public ViewDetail(View view, View parentView) {
    this.view = view;
    this.parentView = parentView;
  }

  public View getView() {
    return view;
  }

  public View getParentView() {
    return parentView;
  }
}
