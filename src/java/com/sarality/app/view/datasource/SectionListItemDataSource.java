package com.sarality.app.view.datasource;

import java.util.ArrayList;
import java.util.List;

import com.sarality.app.view.list.ListItemGroupGenerator;
import com.sarality.app.view.list.SectionListGroup;
import com.sarality.app.view.list.SectionListItem;

public class SectionListItemDataSource<H, I> implements DataSource<SectionListItem<H, I>> {

  private final DataSource<I> source;
  private final ListItemGroupGenerator<H, I> groupGenerator;
  private List<SectionListItem<H, I>> sectionItemList;

  public SectionListItemDataSource(DataSource<I> source, ListItemGroupGenerator<H, I> groupGenerator) {
    this.source = source;
    this.groupGenerator = groupGenerator;
  }

  @Override
  public boolean isStaticDataSet() {
    return false;
  }

  @Override
  public void loadData() {
    source.loadData();
    List<I> dataList = source.getData();
    List<SectionListGroup<H, I>> groupList = groupGenerator.generateGroups(dataList);
    sectionItemList = flattenList(groupList);
  }

  @Override
  public List<SectionListItem<H, I>> getData() {
    return sectionItemList;
  }

  private List<SectionListItem<H, I>> flattenList(List<SectionListGroup<H, I>> sectionList) {
    List<SectionListItem<H, I>> flattenedList = new ArrayList<SectionListItem<H, I>>();
    for (int i = 0; i < sectionList.size(); i++) {
      SectionListGroup<H, I> entry = sectionList.get(i);

      SectionListItem<H, I> itemHeader = new SectionListItem<H, I>(entry.getHeader(), null);
      flattenedList.add(itemHeader);

      for (I entryData : entry.getItems()) {
        SectionListItem<H, I> itemData = new SectionListItem<H, I>(null, entryData);
        flattenedList.add(itemData);
      }
    }
    return flattenedList;
  }
}
