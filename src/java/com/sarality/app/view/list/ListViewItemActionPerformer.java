package com.sarality.app.view.list;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sarality.app.view.action.Action;
import com.sarality.app.view.action.TriggerType;

/**
 * Performs the Action when an event on an Item in a ListView is triggered.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
class ListViewItemActionPerformer implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
    View.OnClickListener, View.OnLongClickListener {

  private final Action<ListViewActionContext> action;
  private final LinearLayout listView;
  private final ListTagIdDefinition tagIdDefinition;

  /**
   * Constructor.
   *
   * @param action Action that needs to be performed on ListView item click.
   */
  ListViewItemActionPerformer(Action<ListViewActionContext> action) {
    this.action = action;
    this.listView = null;
    this.tagIdDefinition = null;
  }

  /**
   * Constructor for NonScrollingListView.
   *
   * @param action Action that needs to be performed on ListView item click.
   * @param listView LinearLayout that in used a ListView.
   */
  ListViewItemActionPerformer(Action<ListViewActionContext> action, LinearLayout listView,
      ListTagIdDefinition tagIdDefinition) {
    this.action = action;
    this.listView = listView;
    this.tagIdDefinition = tagIdDefinition;
  }

  void setup(ListView view, TriggerType triggerType) {
    if (triggerType == TriggerType.CLICK_LIST_ITEM) {
      view.setOnItemClickListener(this);
    } else if (triggerType == TriggerType.LONG_CLICK_LIST_ITEM) {
      view.setOnItemLongClickListener(this);
    }
  }

  void setup(View view, TriggerType triggerType, int position, long rowId) {
    if (triggerType == TriggerType.CLICK_LIST_ITEM) {
      view.setOnClickListener(this);
      view.setTag(tagIdDefinition.getRowPositionTagResource(), position);
      view.setTag(tagIdDefinition.getRowIdTagResource(), rowId);
    } else if (triggerType == TriggerType.LONG_CLICK_LIST_ITEM) {
      view.setOnLongClickListener(this);
      view.setTag(tagIdDefinition.getRowPositionTagResource(), position);
      view.setTag(tagIdDefinition.getRowIdTagResource(), rowId);
    }
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
    action.performAction(new ListViewActionContext(view, parent, position, rowId));
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
    action.performAction(new ListViewActionContext(view, parent, position, id));
    return true;
  }

  @Override
  public void onClick(View view) {
    Integer position = (Integer) view.getTag(tagIdDefinition.getRowPositionTagResource());
    Long rowId = (Long) view.getTag(tagIdDefinition.getRowIdTagResource());
    action.performAction(new ListViewActionContext(view, listView,
        position == null ? -1 : position, rowId == null ? -1 : rowId));
  }

  @Override
  public boolean onLongClick(View view) {
    Integer position = (Integer) view.getTag(tagIdDefinition.getRowPositionTagResource());
    Long rowId = (Long) view.getTag(tagIdDefinition.getRowIdTagResource());
    action.performAction(new ListViewActionContext(view, listView,
        position == null ? -1 : position, rowId == null ? -1 : rowId));
    return true;
  }
}
