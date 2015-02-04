package com.sarality.app.view.list;

import com.sarality.app.view.action.Action;
import com.sarality.app.view.action.TriggerType;

/**
 * Wrapper for the Action to be triggered on List View.
 * <p/>
 * Makes it easier to store a mapping between the triggers and the actions.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ListViewAction {
  private final TriggerType triggerType;
  private final Action<ListViewActionContext> action;

  ListViewAction(TriggerType triggerType,
      Action<ListViewActionContext> action) {
    this.triggerType = triggerType;
    this.action = action;
  }

  TriggerType getTriggerType() {
    return triggerType;
  }

  Action<ListViewActionContext> getAction() {
    return action;
  }
}
