package com.sarality.app.view.list;

import java.util.List;

/**
 * Generates the list of sections with section Headers given the dataList It groups the original list of items into
 * different sections and defines a section header for each of the sections as well
 * 
 * @author sunayna@dothat.in sunayna
 * @param H : Section Header
 * @param I : Item within the the Section
 */
public interface ListItemGroupGenerator<H,I> {

  /**
   * Generates the list of groups of items each group with its own list of items and section header
   * 
   * @param list : List from the DataSource
   * @return : New List with Section Headers
   */
  public List<SectionListGroup<H,I>> generateGroups(List<?> list);
}
