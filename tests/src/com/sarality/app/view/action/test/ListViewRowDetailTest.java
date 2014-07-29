package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.sarality.app.view.action.ListViewRowDetail;

/**
 * Tests for {@link ListViewRowDetail}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ListViewRowDetailTest extends BaseUnitTest {

  public void testListViewRowDetail() {
    Context context = getInstrumentation().getTargetContext().getApplicationContext();
    View view = mock(View.class);
    AdapterView<ListAdapter> parent = new TestListView(context);
    int position = 2;
    long rowId = 5;
    ListViewRowDetail rowViewDetail = new ListViewRowDetail(view, parent, position, rowId);

    assertNotNull(rowViewDetail);
    assertEquals(position, rowViewDetail.getPosition());
    assertEquals(rowId, rowViewDetail.getRowId());
    assertEquals(parent, rowViewDetail.getParentView());
    assertEquals(view, rowViewDetail.getView());
  }
}

class TestListView extends AdapterView<ListAdapter> {

  public TestListView(Context context) {
    super(context);
  }

  @Override
  public ListAdapter getAdapter() {
    return null;
  }

  @Override
  public View getSelectedView() {
    return null;
  }

  @Override
  public void setAdapter(ListAdapter arg0) {
  }

  @Override
  public void setSelection(int arg0) {
  }
}
