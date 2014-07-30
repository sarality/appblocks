package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;

import org.mockito.Mockito;

import android.view.View;

import com.sarality.app.view.action.test.BaseUnitTest;
import com.sarality.app.view.list.ListRowViewCache;

/**
 * Tests for {@link ListRowViewCache}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ListRowViewCacheTest extends BaseUnitTest {

  public void testViewCache() {
    int viewId = 1234;
    ListRowViewCache rowViewCache = new ListRowViewCache();
    View rowView = mock(View.class);
    View view = mock(View.class);

    Mockito.when(rowView.findViewById(viewId)).thenReturn(view);
    rowViewCache.cacheViewWithId(rowView, viewId);
    assertEquals(view, rowViewCache.getViewById(rowView, viewId));
  }

  public void testGetViewId_withNoCache() {
    int viewIdCached = 1234;
    int viewIdNotCached = 5678;
    ListRowViewCache rowViewCache = new ListRowViewCache();
    View rowView = mock(View.class);
    View view = mock(View.class);

    Mockito.when(rowView.findViewById(viewIdCached)).thenReturn(view);
    Mockito.when(rowView.findViewById(viewIdNotCached)).thenReturn(view);
    // Cache only one vieId-View
    rowViewCache.cacheViewWithId(rowView, viewIdCached);

    // Should return view from rowView rather than cache
    rowViewCache.getViewById(rowView, viewIdNotCached);
    assertEquals(view, rowView.findViewById(viewIdNotCached));
  }
}
