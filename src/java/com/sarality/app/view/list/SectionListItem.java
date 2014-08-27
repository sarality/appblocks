package com.sarality.app.view.list;

import com.sarality.app.data.BaseFieldBasedDataObject;

/**
 * Creates the ability to create Section Headers and Section Data A common listitem that would either have a section
 * header or section data
 * 
 * @author sunayna@dothat.in sunayna
 * @param <H,I> : Section Header and Section Item
 */
public class SectionListItem<H, I> extends BaseFieldBasedDataObject<SectionListItem<H, I>> {
  private boolean isSectionHeader;
  private H sectionHeader;
  private I sectionData;

  @Override
  public Builder<H, I> newBuilder() {
    return new Builder<H, I>();
  }

  /**
   * Returns if the current item is a section header
   * 
   * @return
   */
  public boolean isSectionHeader() {
    return isSectionHeader;
  }

  /**
   * Returns the section Header if the item is of type Section header
   * 
   * @return
   */
  public H getSectionHeader() {
    if (isSectionHeader)
      return sectionHeader;
    return null;
  }

  /**
   * Returns the section data
   * 
   * @return
   */
  public I getData() {
    if (!isSectionHeader)
      return sectionData;
    return null;
  }

  /**
   * Builder for creating the SectionListItem
   * 
   * @author sunayna@dothat.in sunayna
   * @param <T>
   */
  public static class Builder<H, I> extends BaseFieldBasedDataObject.Builder<SectionListItem<H, I>> {

    @Override
    protected SectionListItem<H, I> newDataObject() {
      return new SectionListItem<H, I>();
    }

    public Builder<H, I> setSectionHeader(H sectionHeader) {
      data.sectionHeader = sectionHeader;
      data.isSectionHeader = true;
      return this;
    }

    public Builder<H, I> setSectionData(I sectionData) {
      data.sectionData = sectionData;
      data.isSectionHeader = false;
      return this;
    }
  }
}
