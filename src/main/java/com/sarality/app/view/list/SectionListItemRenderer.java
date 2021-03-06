package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.action.ComponentActionManager;

/**
 * Renders both the SectionHeader and the content in each of the sections
 * 
 * @author sunayna@dothat.in sunayna
 * @param <H,I> : Section Header and Section Item
 */
public class SectionListItemRenderer<H, I> extends BaseListRowRenderer<SectionListItem<H, I>> implements
    ListRowRenderer<SectionListItem<H, I>> {
  private final ListRowRenderer<H> headerRenderer;
  private final ListRowRenderer<I> itemRenderer;

  /**
   * Constructor
   * 
   * @param headerRenderer
   * @param itemRenderer
   */
  public SectionListItemRenderer(ListRowRenderer<H> headerRenderer, ListRowRenderer<I> itemRenderer) {
    this.headerRenderer = headerRenderer;
    this.itemRenderer = itemRenderer;
  }

  @Override
  public int getRowLayout(SectionListItem<H, I> value) {
    if (value.isSectionHeader()) {
      return headerRenderer.getRowLayout(value.getSectionHeader());
    } else {
      return itemRenderer.getRowLayout(value.getData());
    }
  }

  @Override
  public void populateViewCache(View rowView, ListRowViewCache viewCache, SectionListItem<H, I> value) {
    if (value.isSectionHeader()) {
      headerRenderer.populateViewCache(rowView, viewCache, value.getSectionHeader());
    } else {
      itemRenderer.populateViewCache(rowView, viewCache, value.getData());
    }
  }

  @Override
  public void render(View rowView, ListRowViewCache viewCache, SectionListItem<H, I> value) {
    if (value.isSectionHeader()) {
      headerRenderer.render(rowView, viewCache, value.getSectionHeader());
    } else {
      itemRenderer.render(rowView, viewCache, value.getData());
    }
  }

  @Override
  // TODO animations should be loaded in the component and then started in the adapter.
  public int getAnimation(View rowView, SectionListItem<H, I> rowValue) {
    if (rowValue.isSectionHeader()) {
      return headerRenderer.getAnimation(rowView, rowValue.getSectionHeader());
    } else {
      return itemRenderer.getAnimation(rowView, rowValue.getData());
    }
  }

  @Override
  public void setupActions(View rowView, ListRowViewCache rowViewCache, SectionListItem<H, I> value,
      ComponentActionManager componentManager) {
    if (!value.isSectionHeader()) {
      super.setupActions(rowView, rowViewCache, value, componentManager);
    }
  }
}
