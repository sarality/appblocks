package com.sarality.app.view.list;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.sarality.app.view.datasource.DataSource;

/**
 * ListComponent Object that works on a list and adds the ability to add section headers to the list
 * 
 * @author sunayna@dothat.in sunayna
 * @param <T>
 */
public abstract class SectionListComponent<T> extends ListComponent<SectionListItem<T>> {

  private final List<String> sectionHeaderList = new ArrayList<String>();
  private final FragmentActivity activity;

  /**
   * Constructor
   * 
   * @param activity - FragmentActivity on which the listView is created
   * @param view - the listview to be populated and rendered
   * @param sectionRenderer - custom renderer as specified by the activity
   */
  public SectionListComponent(FragmentActivity activity, ListView view,
      ListRowRenderer<SectionListItem<T>> sectionRenderer) {
    super(activity, view, sectionRenderer);
    this.activity = activity;
  }

  /**
   * Initializes the data for the listview
   * 
   * @param dataSource
   */
  public void initSource(DataSource<T> dataSource) {
    ListComponentLoader<T> loader = new ListComponentLoader<T>(activity, dataSource);
    renderData(loader.loadData());
  }

  /**
   * Creates section headers and adds to the sectionHeaderList to create a list of all sectionHeaders
   * 
   * @param category - Name of the SectionHeader to be added
   * @param newList - List of sectionListItems
   */
  public void addSectionHeader(String sectionHeader, List<SectionListItem<T>> newList) {
    if (!sectionHeaderList.contains(sectionHeader)) {
      SectionListItem<T> rData = new SectionListItem.Builder<T>().setSectionHeader(sectionHeader).build();
      newList.add(rData);
      sectionHeaderList.add(sectionHeader);
    }
  }

  /**
   * Categorize the list into sections
   * 
   * @param dataList - original dataList
   * @return - dataList categorized properly with Section Headers
   */
  public abstract List<SectionListItem<T>> categorize(List<T> dataList);

  /**
   * Renders the Data after categorizing it
   * 
   * @param dataList
   */
  public void renderData(List<T> dataList) {
    super.render(categorize(dataList));
  }
}
