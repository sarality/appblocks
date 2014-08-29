package com.sarality.app.view.list;

import java.util.List;

/**
 * Section Group for a set of list items This group would have a header and a list of in it.
 * 
 * @author sunayna@dothat.in sunayna
 * @param <H> Section Header
 * @param <I> Items within that section
 */
public class SectionListGroup<H, I> {

  private H header;
  private List<I> items;

  /**
   * Constructors
   */
  public SectionListGroup() {
  }

  public SectionListGroup(H header, List<I> items) {
    this.header = header;
    this.items = items;
  }

  /**
   * Sets the Header for this group
   * 
   * @param header
   */
  public void setHeader(H header) {
    this.header = header;
  }

  /**
   * Gets the header object set either by the constructor or setHeader()
   * 
   * @return Header Object
   */
  public H getHeader() {
    return header;
  }

  /**
   * Gets the list of items for this group , set either by the constructor or by setItems()
   * 
   * @return Items for this group
   */
  public List<I> getItems() {
    return items;
  }

  /**
   * Sets the items for this group
   * 
   * @param items
   */
  public void setItems(List<I> items) {
    this.items = items;
  }

  /**
   * Remove all the items from the group
   */
  public void removeItems() {
    this.items = null;
  }

  /**
   * Remove a particular item from the group
   * 
   * @param item
   */
  public void removeItem(I item) {
    items.remove(item);
  }

  /**
   * Adds one item at a time to this group
   * 
   * @param item
   */
  public void addItem(I item) {
    items.add(item);
  }
}
