package com.sarality.app.view.list;

import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.sarality.app.view.action.ComponentActionManager;
import com.sarality.app.view.datasource.DataSource;

/**
 * ListComponent Object that works on a list and adds the ability to add section headers to the list
 * 
 * @author sunayna@dothat.in sunayna
 * @param <T>
 */
public class SectionListComponent<H, I> extends ListComponent<I> {

  private final FragmentActivity activity;
  private ListItemGenerator<SectionListItem<H, I>> listGenerator;
  private final SectionListItemRenderer<H, I> sectionListItemRenderer;

  /**
   * Constructor
   * 
   * @param activity - FragmentActivity on which the listView is created
   * @param view - the listview to be populated and rendered
   * @param sectionRenderer - custom renderer as specified by the activity
   */
  public SectionListComponent(FragmentActivity activity, ListView view, ListRowRenderer<H> sectionHeaderRenderer,
      ListRowRenderer<I> sectionItemRenderer, ListItemGenerator<SectionListItem<H, I>> listGenerator) {
    super(activity, view, sectionItemRenderer);
    this.sectionListItemRenderer = new SectionListItemRenderer<H, I>(sectionHeaderRenderer, sectionItemRenderer);
    this.activity = activity;
    this.listGenerator = listGenerator;
  }

  /**
   * Initializes the list from the dataSource and then also generates the complete list of data with section headers
   * 
   * @param dataSource
   */
  public void initSource(DataSource<I> dataSource) {
    List<I> listItems = super.init(dataSource);
    List<SectionListItem<H, I>> selectList = listGenerator.generateList(listItems);
    setComponentAdapter(selectList);
  }

  /**
   * Creates the adapter using the SectionListItem list and sets the adapter for the view
   * 
   * @param selectList
   */
  private void setComponentAdapter(List<SectionListItem<H, I>> selectList) {
    ComponentActionManager componentManager = new ComponentActionManager(getRowActions());
    SectionListAdapter<H, I> adapter = new SectionListAdapter<H, I>(activity, sectionListItemRenderer, selectList,
        componentManager);
    view.setAdapter(adapter);
  }
}
