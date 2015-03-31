package com.sarality.app.view.list;

import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;

import java.util.List;

/**
 * Initializes a List View with Sections.
 *
 * @param <T> Type of data displayed in the list.
 * @param <H> Type of data needed to display section header.
 * @author abhideep@ (Abhideep Singh)
 */
public class SectionedListViewInitializer<T, H> extends ListViewInitializer<SectionListItem<H,T>> {

  private final ListSectionGenerator<H, T> sectionGenerator;

  public SectionedListViewInitializer(FragmentActivity activity, ListView view,
      ListViewRowRenderer<T> rowRenderer, ListSectionGenerator<H, T> sectionGenerator,
      ListViewRowRenderer<H> headerRenderer,
      int loaderId) {
    super(activity, view, new SectionedListViewItemRenderer<H, T>(rowRenderer, headerRenderer), loaderId);
    this.sectionGenerator = sectionGenerator;
  }

  public SectionedListViewInitializer(FragmentActivity activity, ListView view,
      ListViewRowRenderer<T> rowRenderer, ListSectionGenerator<H, T> sectionGenerator,
      ListViewRowRenderer<H> headerRenderer) {
    super(activity, view, new SectionedListViewItemRenderer<H, T>(rowRenderer, headerRenderer));
    this.sectionGenerator = sectionGenerator;
  }

  public void init(List<T> data) {
    super.init(getSectionedList(data, sectionGenerator));
  }

  private List<SectionListItem<H, T>> getSectionedList(List<T> rowValueList,
      ListSectionGenerator<H, T> listSectionGenerator) {
    List<SectionListItem<H, T>> sectionListItems = Lists.emptyList();
    List<H> headerList = listSectionGenerator.generateSections(rowValueList);
    int ctr = 0;
    for (H header : headerList) {
      sectionListItems.add(new SectionListItem<H, T>(header, null));
      List<T> items = listSectionGenerator.getSectionItems(ctr);
      for (T item : items) {
        sectionListItems.add(new SectionListItem<H, T>(null, item));
      }
      ctr++;
    }
    return sectionListItems;
  }
}
