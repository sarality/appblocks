package com.sarality.app.view.list;

/**
 * Creates the ability to create Section Headers and Section Data A common listitem that would either have a section
 * header or section data
 * 
 * @author sunayna@dothat.in sunayna
 * @param <H> Section Header
 * @param <I> Section Item
 */
public class SectionListItem<H, I> {
  private boolean isSectionHeader;
  private H sectionHeader;
  private I sectionData;

  /**
   * Returns if the current item is a section header
   * 
   * @return
   */
  public final boolean isSectionHeader() {
    return isSectionHeader;
  }

  /**
   * Returns the section Header if the item is of type Section header
   * 
   * @return
   */
  public final H getSectionHeader() {
    return sectionHeader;
  }

  /**
   * Returns the section data
   * 
   * @return
   */
  public final I getData() {
    return sectionData;
  }

  public final void setSectionHeader(H sectionHeader) {
    this.sectionHeader = sectionHeader;
    this.isSectionHeader = true;
  }

  public final void setSectionData(I sectionData) {
    this.sectionData = sectionData;
    this.isSectionHeader = false;

  }
}
