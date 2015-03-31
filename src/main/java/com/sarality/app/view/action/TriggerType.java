package com.sarality.app.view.action;

/**
 * Type of event that triggers an action.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public enum TriggerType {
  CLICK, // A View is Clicked
  CLICK_LIST_ITEM, // An Item in a ListView is Clicked
  DIALOG_CANCEL, // When the dialog is Cancelled
  DIALOG_POSITIVE, // When the button to perform the action is pressed e.g. Yes, OK, Confirm, Select
  DIALOG_NEGATIVE, // When the button that cancels the current action is pressed e.g. No, Cancel
  DIALOG_NEUTRAL, // When the button for an alternate action is pressed e.g. Remind Me Later
  LONG_CLICK, // A view is Long Clicked
  LONG_CLICK_LIST_ITEM, // An Item in a ListView is Long Clicked
  TOUCH, // A View is Touched. A Touch is composed to a Touch Down and a Touch Up event
  TOUCH_UP, // Event when a View is being touched
  TOUCH_DOWN, // Event when the View was being touched but is now NOT being touched.
  ON_SUCCESS // Deprecated : Action to run when previous action runs successfully
}
