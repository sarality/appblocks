package com.sarality.app.view.list;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.common.collect.Maps;
import com.sarality.app.view.action.Action;
import com.sarality.app.view.action.TriggerType;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility class for registration and setup of actions for a ListViewInitializer.
 * <p/>
 * Allows for the reuse of functionality across multiple implementations of ListViewInitializer.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ListActionManager {

  private final ListTagIdDefinition tagIdDefinition;
  private final Set<TriggerType> supportedTriggerTypes;

  private final Map<TriggerType, ListViewAction> actionMap = Maps.empty();
  private final List<ListViewAction> actionList = Lists.emptyList();

  ListActionManager(Set<TriggerType> supportedTriggerTypes, ListTagIdDefinition tagIdDefinition) {
    this.supportedTriggerTypes = supportedTriggerTypes;
    this.tagIdDefinition = tagIdDefinition;
  }

  void register(TriggerType triggerType, Action<ListViewActionContext> action) {
    assertValidAction(triggerType);
    ListViewAction viewAction = new ListViewAction(triggerType, action);
    actionMap.put(triggerType, viewAction);
    actionList.add(viewAction);
  }

  protected boolean assertValidAction(TriggerType triggerType) {
    if (!supportedTriggerTypes.contains(triggerType)) {
      throw new IllegalArgumentException("Cannot register a " + triggerType + " actions for the ListView. Only the " +
          "following triggers are supported on the view " + supportedTriggerTypes);
    }

    // Check if an action with the trigger type already exists on the view
    if (actionMap.get(triggerType) != null) {
      throw new IllegalArgumentException("Cannot register multiple " + triggerType + " actions for the ListView.");
    }
    return true;
  }

  void setup(ListView listView) {
    for (ListViewAction action : actionList) {
      new ListViewItemActionPerformer(action.getAction()).setup(listView, action.getTriggerType());
    }
  }

  void setup(LinearLayout listView, View rowView, int position, long rowId) {
    for (ListViewAction action : actionList) {
      new ListViewItemActionPerformer(action.getAction(), listView, tagIdDefinition)
          .setup(rowView, action.getTriggerType(), position, rowId);
    }
  }
}
