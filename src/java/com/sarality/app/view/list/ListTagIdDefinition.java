package com.sarality.app.view.list;

/**
 * Additional class that must be defined for NonScrollingListViewInitializer.
 * <p/>
 * Since we use a LinearLayout for Non Scrolling List, we need to save the RowId and position with each row as Tags.
 * These tags must have unique values and hence the requirement to define them as an app resource and passing in the
 * id for the resource.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ListTagIdDefinition {
  private final int rowPositionTagResource;
  private final int rowIdTagResource;

  public ListTagIdDefinition(int rowPositionTagResource, int rowIdTagResource) {
    this.rowPositionTagResource = rowPositionTagResource;
    this.rowIdTagResource = rowIdTagResource;
  }

  public int getRowPositionTagResource() {
    return rowPositionTagResource;
  }

  public int getRowIdTagResource() {
    return rowIdTagResource;
  }
}
