package com.sarality.app.view.list;

import java.util.List;

/**
 * Interface for classes that generate sections for the given list of objects.
 *
 * @param <T> Type of data displayed in the list.
 * @param <H> Type of data needed to display section header.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface ListSectionGenerator<H, T> {

  /**
   * Generate data for the sections of the given list.
   * <p/>
   * It assumed that the items belonging to the section can then be extracted via the section index.
   *
   * @param listItems List of items to create sections from.
   * @return List of data for the sections
   */
  List<H> generateSections(List<T> listItems);

  /**
   * Return list of items in a given section.
   *
   * @param sectionIndex Index of the section.
   * @return List of items in the section.
   */
  List<T> getSectionItems(int sectionIndex);
}
