package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.BaseViewRenderer;

/**
 * Composite Renderer that delegates to the Renderer for both the Section Header and Rows of List View.
 * 
 * @param <H> : Type of Data for the Section Header
 * @param <T> : Type of Data for ListView Item
 * @author abhideep@ (Abhideep Singh)
 */
public class SectionedListViewItemRenderer<H, T> extends BaseViewRenderer<View, SectionListItem<H, T>>
    implements ListViewRowRenderer<SectionListItem<H, T>> {

  private final ListViewRowRenderer<T> itemRenderer;
  private final ListViewRowRenderer<H> headerRenderer;

  /**
   * Constructor
   *
   * @param headerRenderer Renderer for the Section Header
   * @param itemRenderer Renderer for the Row in the section
   */
  public SectionedListViewItemRenderer(ListViewRowRenderer<T> itemRenderer, ListViewRowRenderer<H> headerRenderer) {
    this.itemRenderer = itemRenderer;
    this.headerRenderer = headerRenderer;
  }

  @Override
  public int getRowLayout(int position, SectionListItem<H, T> value) {
    if (value.isSectionHeader()) {
      return headerRenderer.getRowLayout(position, value.getSectionHeader());
    } else {
      return itemRenderer.getRowLayout(position, value.getData());
    }
  }

  @Override
  public int getViewTypeCount() {
    return headerRenderer.getViewTypeCount() + itemRenderer.getViewTypeCount();
  }

  @Override
  public int getViewType(int position, SectionListItem<H, T> value) {
    if (value.isSectionHeader()) {
      return headerRenderer.getViewType(position, value.getSectionHeader());
    }
    return headerRenderer.getViewTypeCount() + itemRenderer.getViewType(position, value.getData());
  }

  @Override
  public void renderView(View rowView, SectionListItem<H, T> value) {
    if (value.isSectionHeader()) {
      headerRenderer.render(rowView, value.getSectionHeader());
    } else {
      itemRenderer.render(rowView, value.getData());
    }
  }
}
