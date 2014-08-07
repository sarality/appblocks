package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;
import android.app.Activity;
import android.view.View;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.list.ListComponentAdapter;
import com.sarality.app.view.list.ListRowRenderer;

/**
 * Tests for {@link ListComponentAdapter}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ListComponentAdapterTest extends TestCase {

  public void testGetCount_NullList() {
    List<String> rowValueList = Lists.of();
    Activity context = mock(Activity.class);
    ListComponentAdapter<String> adapter = new ListComponentAdapter<String>(context, null, rowValueList, null);

    assertEquals(0,adapter.getCount());
  }

  public void testGetCount() {
    List<String> rowValueList = Lists.of("Test1" , "Test2");
    Activity context = mock(Activity.class);
    ListComponentAdapter<String> adapter = new ListComponentAdapter<String>(context, null, rowValueList, null);

    assertEquals(rowValueList.size(),adapter.getCount());
  }
  
  public void testListComponentAdapter() {
    Activity context = mock(Activity.class);
    ListComponentAdapter<String> adapter = new ListComponentAdapter<String>(context, null, null, null);

    assertNotNull(adapter);
    assertEquals(context,adapter.getContext());
  }

  @SuppressWarnings("unchecked")
  public void testGetView() {
    Activity context = mock(Activity.class);
    List<String> rowValueList = Lists.of("Test1" , "Test2");
    View view = mock(View.class);
    ListRowRenderer<String> rowRenderer = mock(ListRowRenderer.class);
    ListComponentAdapter<String> adapter = new ListComponentAdapter<String>(context, rowRenderer, rowValueList, null);
    
    adapter.getView(0, view, null);
  }

}
