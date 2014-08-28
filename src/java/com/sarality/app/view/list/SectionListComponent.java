package com.sarality.app.view.list;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.sarality.app.view.action.ComponentActionManager;

/**
 * ListComponent Object that works on a list and adds the ability to add section headers to the list
 * 
 * @author sunayna@dothat.in sunayna
 * 
 * @param <H> Section Header Data
 * @param <I> Section List item Data
 */
public class SectionListComponent<H, I> extends ListComponent<I> {

  private final FragmentActivity activity;
  private final ListItemGroupGenerator<H, I> groupGenerator;
  private final SectionListItemRenderer<H, I> sectionListItemRenderer;

  /**
   * Constructor
   * 
   * @param activity - FragmentActivity on which the listView is created
   * @param view - the listview to be populated and rendered
   * @param sectionRenderer - custom renderer as specified by the activity
   */
  public SectionListComponent(FragmentActivity activity, ListView view, ListRowRenderer<H> sectionHeaderRenderer,
      ListRowRenderer<I> sectionItemRenderer, ListItemGroupGenerator<H, I> groupGenerator) {
    super(activity, view, sectionItemRenderer);
    this.sectionListItemRenderer = new SectionListItemRenderer<H, I>(sectionHeaderRenderer, sectionItemRenderer);
    this.activity = activity;
    this.groupGenerator = groupGenerator;
  }

  @Override
  protected void createAdapter(List<I> data){
    List<SectionListGroup<H, I>> groupList = groupGenerator.generateGroups(data);
    List<SectionListItem<H, I>> selectList = flattenList(groupList);
    ComponentActionManager componentManager = new ComponentActionManager(getRowActions());
    SectionListAdapter<H, I> adapter = new SectionListAdapter<H, I>(activity, sectionListItemRenderer, selectList,
        componentManager);
    setAdapter(adapter);
  }

  private List<SectionListItem<H, I>> flattenList(List<SectionListGroup<H, I>> sectionList){
    List<SectionListItem<H, I>>  flattenedList = new ArrayList<SectionListItem<H, I>>();
    for( int i=0; i< sectionList.size(); i++){
      SectionListGroup<H, I> entry = sectionList.get(i);
      
      SectionListItem<H,I> itemHeader = new SectionListItem<H,I>(entry.getHeader(), null);
      flattenedList.add(itemHeader);
      
      for(I entryData: entry.getItems()){
        SectionListItem<H,I> itemData = new SectionListItem<H,I>(null,entryData);
        flattenedList.add(itemData);
      }
    }
    return flattenedList;
  }
}
