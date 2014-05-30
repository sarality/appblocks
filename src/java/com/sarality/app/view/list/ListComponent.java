package com.sarality.app.view.list;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.datasource.DataSource;

/**
 * Component used to render a ListView and setup the associated actions for it.
 * 
 * @author abhideep@ (Abhideep Singh)
 *
 * @param <T> Type of data that is used to render the ListView.
 */
public class ListComponent<T> {
  private static final String TAG = "ListComponent";
  
  // Activity where this List is rendered.
  private final Activity activity;

  // The view associated with the List to be rendered.
  private final ListView view;
  private final ListRowRenderer<T> rowRenderer;

  // List of actions to be setup on the List.
  private List<ViewAction> actionList = Lists.of();
  
  // List of actions to be setup on each row in the List.
  private List<ViewAction> rowActionList = Lists.of();

  // The source for the data used to render the List.
  private DataSource<T> dataSource;
  
  // Adapter used to render the List.
  private ListComponentAdapter<T> adapter;
  
  public ListComponent(Activity activity, ListView view, ListRowRenderer<T> rowRenderer) {
    this.activity = activity;
    this.view = view;
    this.rowRenderer = rowRenderer;
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
  public void init(DataSource<T> source) {
    //TODO(abhideep): Add Async support here
    dataSource = source;
    dataSource.loadData();

    List<T> dataList = dataSource.getData();
    adapter = new ListComponentAdapter<T>(activity, rowRenderer, dataList, rowActionList);
    Log.v(TAG, "List Component will display " + dataList.size() + " items");

    // TODO(abhideep): Treat static and dynamic data sources differently
    view.setAdapter(adapter);
  }
}
