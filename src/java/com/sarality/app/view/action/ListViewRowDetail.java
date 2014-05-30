package com.sarality.app.view.action;

import android.view.View;
import android.widget.AdapterView;

/**
 * Details about a ListView Row that triggered an Action.
 * 
 * @author abhideep@ (Abhideep Singh)
 */
public class ListViewRowDetail extends ViewDetail {
  // Position of row in the list
  private final int position;
  // If associated with the row
  private final long rowId;
  
  /**
   * Constructor.
   * 
   * @param view View for the row which triggers the action.
   * @param parent The ListView that this trigger row view belongs to.
   * @param position The position of the the row in the list.
   * @param rowId The Id of the row that triggers the action.
   */
  public ListViewRowDetail(View view, AdapterView<?> parent, int position, long rowId) {
    super(view, parent);
    this.position = position;
    this.rowId = rowId;
  }

  /**
   * @return Position of the row in the table.
   */
  public int getPosition() {
    return position;
  }

  /**
   * @return Id of the row that trigger the action.
   */
  public long getRowId() {
    return rowId;
  }
}
