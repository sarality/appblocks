package com.sarality.app.view.list;


import java.util.List;

import android.util.Log;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.action.ViewAction;

/**
 * Component used to render a ListView and setup the associated actions for it.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of data that is used to render the ListView.
 */
public class ListComponent<T> {
  private static final String TAG = "ListComponent";
  
  // The view associated with the List to be rendered.
  private final ListView view;

  // List of actions to be setup on the List.
  private List<ViewAction> actionList = Lists.of();
  
  // List of actions to be setup on each row in the List.
  private List<ViewAction> rowActionList = Lists.of();
  
  public ListComponent(ListView view) {
    this.view = view;
  }

  /**
   * @return ListView that contains the List
   */
  public ListView getView() {
    return view;
  }

  /**
   * Register an action for each row in the List.
   * 
   * @param action Action to be setup for each row of the List.
   */
  public void registerRowAction(ViewAction action) {
    rowActionList.add(action);
  }

  /**
   * Register an action for the entire list.
   * <p>
   * ListItemListener based actions are setup here as well
   * 
   * @param action Action to be setup for the List.
   */
  public void registerAction(ViewAction action) {
    actionList.add(action);
  }
  
  /**
   * @return List of Actions that are associated with each row in the List.
   */
  public List<ViewAction> getRowActions() {
    return rowActionList;
  }

  /**
   * Initialize the ListComponent with the given DataSource
   * 
   * @param source
   */
  public void setAdapter(ListComponentAdapter<T> adapter) {
    Log.d(TAG, "setting adapter");
    view.setAdapter(adapter);
  }
  
}
