package com.sarality.app.view.list;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.sarality.app.view.action.ComponentActionManager;
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
  
  // Activity where this List is rendered.
  private final FragmentActivity activity;
 
  // The view associated with the List to be rendered.
  private final ListView view;
  private final ListRowRenderer<T> rowRenderer;
  
  // List of actions to be setup on the List.
  private List<ViewAction> actionList = new ArrayList<ViewAction>();
  
  // List of actions to be setup on each row in the List.
  private List<ViewAction> rowActionList = new ArrayList<ViewAction>();
  
  //Adapter used to render the List.
  private ListComponentAdapter<T> adapter;
  
  private ListComponentLoaderCallback<T>  loaderCallback;
  
  private ListComponentLoader<T> loader;
  
  public ListComponent(FragmentActivity activity, ListView view, ListRowRenderer<T> rowRenderer) {
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
    //Load the data
    loader = new ListComponentLoader<T>(activity, source);
    render(loader.loadData());
  }

  /**
   * Initialize the ListComponent to load data Asynchronously
   * 
   * @param source
   */
   public void  initAsync(DataSource<T> dataSource) {
    loaderCallback = new ListComponentLoaderCallback<T>(activity, dataSource, this);
    activity.getSupportLoaderManager().initLoader(0, null, loaderCallback).forceLoad();
  }

   /**
    * Render the view with the data
    * 
    * @param data
    */
  public void render(List<T> data){
    ComponentActionManager componentActionManager = new ComponentActionManager(getRowActions());
    adapter = new ListComponentAdapter<T>(activity, rowRenderer, data, componentActionManager);
    view.setAdapter(adapter);
  }

  /**
   * Refresh the data from the source and re-render
   * 
   * @param 
   */
  public void refresh() {
    if(loaderCallback!= null){
      loaderCallback.onContentChanged();
    }
    else {// Sync Load
      adapter.reinitalize(loader.loadData());
    }
  }
}