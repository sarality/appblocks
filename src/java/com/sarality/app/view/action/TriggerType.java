package com.sarality.app.view.action;

/**
 * Type of event that triggers an action.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public enum TriggerType {
  CLICK,
  CLICK_LIST_ITEM,
  DIALOG_CANCEL,
  DIALOG_POSITIVE,
  DIALOG_NEGATIVE,
  DIALOG_NEUTRAL,
  LONG_CLICK,
  LONG_CLICK_LIST_ITEM,
  TOUCH,
  TOUCH_UP,
  TOUCH_DOWN,
  ON_SUCCESS,
  ON_FAILURE,
  BEFORE_EXECUTION, 
}
