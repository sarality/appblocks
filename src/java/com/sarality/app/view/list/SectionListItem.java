package com.sarality.app.view.list;

import com.sarality.app.data.BaseFieldBasedDataObject;

/**
 * Creates the ability to create Section Headers and Section Data
 * A common listitem that would either have a section header or section data
 * @author sunayna@dothat.in sunayna
 *
 * @param <T>
 */
public class SectionListItem<T> extends BaseFieldBasedDataObject<SectionListItem<T>>{
  private boolean isSectionHeader;
  private String sectionHeader;
  private T sectionData;

  @Override
  public Builder<T> newBuilder() {
    return new Builder<T>();
  }
  
  /**
   * Returns if the current item is a section header
   * @return 
   */
  public boolean isSectionHeader() {
    return isSectionHeader;
  }

  /**
   * Returns the name of the section Header if the item if of type Section header
   * @return
   */
  public String getSectionHeader() {
    return sectionHeader;
  }

  public T getData() {
    return sectionData;
  }
  
  /**
   * Builder for creating the SectionListItem
   * @author sunayna@dothat.in sunayna
   *
   * @param <T>
   */
  public static class Builder<T> extends BaseFieldBasedDataObject.Builder<SectionListItem<T>> {

    @Override
    protected SectionListItem<T> newDataObject() {
      return new SectionListItem<T>();
    }
    
    public Builder<T> setSectionHeader(String sectionHeader){
      data.sectionHeader = sectionHeader;
      data.isSectionHeader = true;
      return this;
    }

    public Builder<T> setSectionData(T sectionData){
      data.sectionData = sectionData;
      data.isSectionHeader = false;
      return this;
    }
  }
}
