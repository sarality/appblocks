package com.sarality.app.view.list;

/**
 * Creates the ability to create Section Headers and Section Data A common listitem that would either have a section
 * header or section data
 * 
 * @author sunayna@dothat.in sunayna
 * 
 * @param <H> Header Data
 * @param <I> Item Data
 */
public class SectionListItem<H, I> {
  private final boolean isHeader;
  private final H headerData;
  private final I itemData;

  /**
   * Constructor
   * @param header
   * @param itemData
   */
  public SectionListItem(H header, I itemData){
    if(header != null){
      isHeader = true;
    }else
    {
      isHeader = false;
    }
    this.headerData = header;
    this.itemData = itemData;
  }
  /**
   * Returns if the current item is a section header
   * 
   * @return
   */
  public final boolean isSectionHeader() {
    return isHeader;
  }

  /**
   * Returns the section Header if the item is of type Section header
   * 
   * @return
   */
  public final H getSectionHeader() {
    return headerData;
  }

  /**
   * Returns the section data
   * 
   * @return
   */
  public final I getData() {
    return itemData;
  }
}
