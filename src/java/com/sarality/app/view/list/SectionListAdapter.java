package com.sarality.app.view.list;

import java.util.List;

import android.content.Context;

import com.sarality.app.view.action.ComponentActionManager;

/**
 * Custom Adapter that would be used with SectionListComponent
 * 
 * @author sunayna@dothat.in sunayna
 * @param <H> : Section Header
 * @param <T> : Section Data Item
 */
public class SectionListAdapter<H, T> extends ListComponentAdapter<SectionListItem<H, T>> {

  public SectionListAdapter(Context context, ListRowRenderer<SectionListItem<H, T>> rowRenderer,
      List<SectionListItem<H, T>> rowValueList, ComponentActionManager componentManager) {
    super(context, rowRenderer, rowValueList, componentManager);
  }

  @Override
  public int getViewTypeCount() {
    return 2;
  }

  @Override
  public int getItemViewType(int position) {
    SectionListItem<H,T> item = getItem(position);
    if(item.isSectionHeader())
      return 1;
    return 0;
  }
}
