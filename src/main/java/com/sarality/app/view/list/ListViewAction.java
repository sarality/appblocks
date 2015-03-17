package com.sarality.app.view.list;

import android.view.View;
import android.widget.AdapterView;

import com.sarality.app.view.action.BaseAction;

/**
 * Base implementation of an Action on a ListView.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class ListViewAction extends BaseAction<ListViewActionContext>
    implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
    AdapterView.OnItemSelectedListener {

  public static final int POSITION_UNDEFINED = -1;
  public static final int ROW_ID_UNDEFINED = -1;

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    setActionContext(new ListViewActionContext(view, parent, position, id));
    perform();
  }

  @Override
  public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
    setActionContext(new ListViewActionContext(view, parent, position, id));
    return perform();
  }

  @Override
  public final void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    setActionContext(new ListViewActionContext(view, parent, position, id));
    perform();
  }

  @Override
  public final void onNothingSelected(AdapterView<?> parent) {
    setActionContext(new ListViewActionContext(null, parent, POSITION_UNDEFINED, ROW_ID_UNDEFINED));
    perform();
  }

}
