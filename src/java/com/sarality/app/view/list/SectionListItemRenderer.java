package com.sarality.app.view.list;

import android.view.View;

import com.sarality.app.view.action.ComponentActionManager;

/**
 * Renders both the SectionHeader and the content in each of the sections
 * @author sunayna@dothat.in sunayna
 *
 * @param <T>
 */
public class SectionListItemRenderer<T> extends BaseListRowRenderer<SectionListItem<T>> implements
    ListRowRenderer<SectionListItem<T>> {
   private final ListRowRenderer<SectionListItem<T>> headerRenderer;
   private final ListRowRenderer<T> itemRenderer;

   /**
    * Constructor
    * @param headerRenderer
    * @param itemRenderer
    */
  public SectionListItemRenderer(ListRowRenderer<SectionListItem<T>> headerRenderer, ListRowRenderer<T> itemRenderer) {
    this.headerRenderer = headerRenderer;
    this.itemRenderer = itemRenderer;
  }

  @Override
  public int getRowLayout(SectionListItem<T> value) {
    if (value.isSectionHeader()) {
      return headerRenderer.getRowLayout(value);
    }
    return itemRenderer.getRowLayout(value.getData());
  }

  @Override
  public void populateViewCache(View rowView, ListRowViewCache viewCache, SectionListItem<T> value) {
    if (value.isSectionHeader()) {
      headerRenderer.populateViewCache(rowView, viewCache, value);
      return;
    }
    itemRenderer.populateViewCache(rowView, viewCache, value.getData());
  }

  @Override
  public void render(View rowView, ListRowViewCache viewCache, SectionListItem<T> value) {
    if (value.isSectionHeader()) {
      headerRenderer.render(rowView, viewCache, value);
      return;
    }
    itemRenderer.render(rowView, viewCache, value.getData());
  }

  @Override
  public int getAnimation(View rowView, SectionListItem<T> rowValue) {
    if (!rowValue.isSectionHeader()) {
      return itemRenderer.getAnimation(rowView, rowValue.getData());
    } else
      return 0;
  }

  @Override
  public int getItemViewType(SectionListItem<T> value) {
    if (value.isSectionHeader())
      return 1;
    return 0;
  }

  @Override
  public int getViewTypeCount() {
    return 2;
  }

  @Override
  public void setupActions(View rowView, ListRowViewCache rowViewCache, SectionListItem<T> value,
      ComponentActionManager componentManager) {
    if (value.isSectionHeader()) {
      return;
    }
    super.setupActions(rowView, rowViewCache, value, componentManager);
  }
}
