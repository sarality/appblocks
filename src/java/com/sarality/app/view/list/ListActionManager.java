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

/**
 * Utility class for registration and setup of actions for a ListViewInitializer.
 * <p/>
 * Allows for the reuse of functionality across multiple implementations of ListViewInitializer.
 *
 * @author abhideep@ (Abhideep Singh)
 */
class ListActionManager {

  private final Map<TriggerType, ListViewAction> actionMap = Maps.empty();
  private final List<ListViewAction> actionList = Lists.emptyList();

  void register(TriggerType triggerType, Action<ListViewActionContext> action) {
    ListViewAction viewAction = new ListViewAction(triggerType, action);
    actionMap.put(triggerType, viewAction);
    actionList.add(viewAction);
  }

  void setup(ListView listView) {
    for (ListViewAction action : actionList) {
      new ListViewItemActionPerformer(action.getAction()).setup(listView, action.getTriggerType());
    }
  }

  void setup(LinearLayout listView, View rowView, int position, long rowId) {
    for (ListViewAction action : actionList) {
      new ListViewItemActionPerformer(action.getAction(), listView)
          .setup(rowView, action.getTriggerType(), position, rowId);
    }
  }
}
