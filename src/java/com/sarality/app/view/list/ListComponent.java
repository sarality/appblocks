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
  
  // The view associated with the List to be rendered.
  private final int viewId;
  
  // List of actions to be setup on the List.
  private List<ViewAction> actionList = Lists.of();
  
  // List of actions to be setup on each row in the List.
  private List<ViewAction> rowActionList = Lists.of();
  
  private ListAsyncLoaderManager<T>  asyncLoader;
  
  private final Activity context;
  
  public ListComponent(Activity context, int viewId, DataSource<T> dataSource,
      BaseListRowRenderer<T> renderer) {
    this.context = context;
    this.viewId = viewId;
    asyncLoader = new ListAsyncLoaderManager<T>(context, dataSource, renderer, this);
  }

  
  public ListAsyncLoaderManager<T> getAsyncLoader(){
    return asyncLoader;
  }
  
  /**
   * @return ListView that contains the List
   */
  public ListView getView() {
    return (ListView)context.findViewById(viewId);
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

  public void setAdapter(ListComponentAdapter<T> adapter) {
    final ListView listview = (ListView) context.findViewById(viewId);
    Log.d(TAG, "setting adapter for view " + listview);
    listview.setAdapter(adapter);
  }
  
  public void refresh() {
    asyncLoader.onContentChanged();
  }

  
}
