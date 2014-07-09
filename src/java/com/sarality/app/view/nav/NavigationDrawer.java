package com.sarality.app.view.nav;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.action.ViewAction;

/**
 * Class to render and manage the Navigation Drawer 
 * <p>
 * Example Layout, Content and Drawer List Item files are included in appblocks.
 * If you want to use the Navigation Drawer, simply copy the following files
 * to the res/layout folder of your android app and then edit them to 
 * suit your needs.
 * <p>
 * <ul>
 * <li>appblocks/src/android/res/layout/navigation_drawer_layout.xml</li>
 * <li>appblocks/src/android/res/layout/navigation_drawer_content.xml</li>
 * <li>appblocks/src/android/res/layout/navigation_drawer_list_item.xml</li>
 * </ul>
 * 
 * @author abhideep@dothat.in (Abhideep Singh)
 */
public class NavigationDrawer {
  private ListView drawerList;
  private List<String> labelList = Lists.of();
  private List<ViewAction> actionList = Lists.of();

  private final int drawerListViewId;
  private final int drawerListItemViewId;
  
  /**
   * Constructor
   * 
   * @param drawerListViewId <code>int</code> id of the ListView in the DrawerLayout
   * @param drawerLayoutId <code>int</code> id of the DrawerLayout
   */
  public NavigationDrawer(int drawerListViewId, int drawerListItemViewId) {
    this.drawerListViewId = drawerListViewId;
    this.drawerListItemViewId = drawerListItemViewId;
  }

  /**
   * Called on creation of an activity that renders a navigation drawer
   * 
   * @param activity The Activity that is being created
   */
  public void init(Activity activity) {
    drawerList = (ListView) activity.findViewById(drawerListViewId);

    drawerList.setAdapter(new NavigationDrawerListAdapter(activity, drawerListItemViewId, labelList));
    drawerList.setOnItemClickListener(new ItemClickListener());
    // Display a shadow next to drawer
    // drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
  }

  public void registerListItem(String label, ViewAction action) {
    labelList.add(label);
    actionList.add(action);       
  }

  // TODO(abhideep): Replace with ViewAction
  private class ItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      
    }
  }

  private class NavigationDrawerListAdapter extends ArrayAdapter<String> implements ListAdapter {

    public NavigationDrawerListAdapter(Context context, int textViewResourceId, List<String> items) {
      super(context, textViewResourceId, items);
    }
  }
}
