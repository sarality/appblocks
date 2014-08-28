package com.sarality.app.view.list;

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
public class SectionListComponent<H, I> extends ListComponent<SectionListItem<H,I>> {

  private final FragmentActivity activity;
  private final SectionListItemRenderer<H, I> sectionListItemRenderer;

  /**
   * Constructor
   * 
   * @param activity - FragmentActivity on which the listView is created
   * @param view - the listview to be populated and rendered
   * @param sectionRenderer - custom renderer as specified by the activity
   */
  public SectionListComponent(FragmentActivity activity, ListView view, SectionListItemRenderer<H, I> sectionListItemRenderer) {
    super(activity, view, sectionListItemRenderer);
    this.activity = activity;
    this.sectionListItemRenderer = sectionListItemRenderer;
  }

  @Override
  protected SectionListAdapter<H, I> createAdapter(List<SectionListItem<H,I>> data){
    ComponentActionManager componentManager = new ComponentActionManager(getRowActions());
    SectionListAdapter<H, I> adapter = new SectionListAdapter<H, I>(activity, sectionListItemRenderer, data,
        componentManager);
    return adapter;
  }

  }
