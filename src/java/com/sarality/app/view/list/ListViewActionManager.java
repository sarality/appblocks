package com.sarality.app.view.list;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sarality.app.view.action.AppViewAction;
import com.sarality.app.view.action.ViewActionManager;

/**
 * Class that manages the registration and setup of actions on a ListView.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListViewActionManager extends ViewActionManager {

  private ListViewAction onClickAction;
  private ListViewAction onLongClickAction;

  public ListViewActionManager(Context context) {
    super(context);
  }

  public void setOnItemClickAction(AdapterView.OnItemClickListener action) {
    setOnItemClickAction(new ItemClickListenerAction(action));
  }

  public void setOnItemClickAction(AppViewAction action) {
    setOnItemClickAction(new AppViewActionWrapper(action));
  }

  public void setOnItemLongClickAction(AdapterView.OnItemLongClickListener action) {
    setOnItemLongClickAction(new ItemLongClickListenerAction(action));
  }

  public void setOnItemLongClickAction(AppViewAction action) {
    setOnItemClickAction(new AppViewActionWrapper(action));
  }

  public void setOnItemClickAction(ListViewAction action) {
    if (onClickAction != null) {
      throw new IllegalStateException("Trying to register multiple Actions for a Click event on ListView items. " +
          "Use action chaining instead by all calling registerSuccessAction instead");
    }
    onClickAction = action;
  }

  public void setOnItemLongClickAction(ListViewAction action) {
    if (onLongClickAction != null) {
      throw new IllegalStateException("Trying to register multiple Actions for a Long Click event on ListView items. " +
          "Use action chaining instead by all calling registerSuccessAction instead");
    }
    onLongClickAction = action;
  }

  public void setupListView(ListView view) {
    if (onClickAction != null) {
      view.setOnItemClickListener(onClickAction);
    }

    if (onLongClickAction != null) {
      view.setOnItemLongClickListener(onLongClickAction);
    }
  }

  public void setup(LinearLayout listView, View rowView, int position, int rowId, ListTagIdDefinition tagIdDefinition) {
    if (onClickAction != null) {
      rowView.setTag(tagIdDefinition.getRowPositionTagResource(), position);
      rowView.setTag(tagIdDefinition.getRowIdTagResource(), rowId);
      rowView.setOnClickListener(new SimulatedListViewAction(listView, onClickAction, tagIdDefinition));
    }

    if (onLongClickAction != null) {
      rowView.setTag(tagIdDefinition.getRowPositionTagResource(), position);
      rowView.setTag(tagIdDefinition.getRowIdTagResource(), rowId);
      rowView.setOnLongClickListener(new SimulatedListViewAction(listView, onLongClickAction, tagIdDefinition));
    }
  }

  /**
   * ListViewAction that wraps ab {@code Action}.
   */
  public static class SimulatedListViewAction extends ListViewAction implements View.OnClickListener,
      View.OnLongClickListener {

    private final LinearLayout listView;
    private final ListViewAction action;
    private final ListTagIdDefinition tagIdDefinition;

    public SimulatedListViewAction(LinearLayout listView, ListViewAction action, ListTagIdDefinition tagIdDefinition) {
      this.listView = listView;
      this.action = action;
      this.tagIdDefinition = tagIdDefinition;
    }

    @Override
    public void onClick(View view) {
      Integer position = (Integer) view.getTag(tagIdDefinition.getRowPositionTagResource());
      Long rowId = (Long) view.getTag(tagIdDefinition.getRowIdTagResource());
      setContext(view, listView, null, position, rowId);
      perform();
    }

    @Override
    public boolean onLongClick(View view) {
      Integer position = (Integer) view.getTag(tagIdDefinition.getRowPositionTagResource());
      Long rowId = (Long) view.getTag(tagIdDefinition.getRowIdTagResource());
      setContext(view, listView, null, position, rowId);
      return perform();
    }

    @Override
    protected boolean doAction() {
      if (action != null) {
        return action.perform();
      }
      return true;
    }
  }

  /**
   * ListViewAction that wraps a {@code OnItemClickListener}.
   */
  private class AppViewActionWrapper extends ListViewAction {

    private final AppViewAction action;

    private AppViewActionWrapper(AppViewAction action) {
      this.action = action;
    }

    @Override
    protected void setContext(View view, View listView, AdapterView<?> parent, int position, long id) {
      action.setView(view);
    }

    @Override
    protected boolean doAction() {
      if (action != null) {
        action.perform();
      }
      return true;
    }
  }

  /**
   * ListViewAction that wraps a {@code OnItemClickListener}.
   */
  private class ItemClickListenerAction extends ListViewAction {

    private final AdapterView.OnItemClickListener listener;

    private ItemClickListenerAction(AdapterView.OnItemClickListener listener) {
      this.listener = listener;
    }

    @Override
    protected boolean doAction() {
      if (listener != null) {
        listener.onItemClick(getAdapterView(), getView(), getListPosition(), getRowId());
      }
      return true;
    }
  }

  /**
   * ListViewAction that wraps a {@code OnItemLongClickListener}.
   */
  private class ItemLongClickListenerAction extends ListViewAction {

    private final AdapterView.OnItemLongClickListener listener;

    private ItemLongClickListenerAction(AdapterView.OnItemLongClickListener listener) {
      this.listener = listener;
    }

    @Override
    protected boolean doAction() {
      if (listener != null) {
        listener.onItemLongClick(getAdapterView(), getView(), getListPosition(), getRowId());
      }
      return true;
    }
  }

}
