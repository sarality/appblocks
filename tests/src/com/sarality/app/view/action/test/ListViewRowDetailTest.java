package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.sarality.app.view.action.ListViewRowDetail;

/**
 * Tests for {@link ListViewRowDetail}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ListViewRowDetailTest extends TestCase {
  
  @Test
  public void testListViewRowDetail() {
    View view = mock(View.class);
    @SuppressWarnings("unchecked")
    AdapterView<ListAdapter> parent = mock(AdapterView.class);
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

