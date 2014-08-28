package com.sarality.app.view.list;

import java.util.List;

/**
 * Generates the list of items with section Headers given the dataList
 * 
 * @author sunayna@dothat.in sunayna
 * @param <T> Item that would be either be a header or an item
 */
public interface SectionListItemGenerator<T> {

  /**
   * Generates the complete list by grouping the items into different sections and adding section headers
   * 
   * @param list : List from the DataSource
   * @return : New List with Section Headers
   */
  public List<T> generateList(List<?> list);
}
