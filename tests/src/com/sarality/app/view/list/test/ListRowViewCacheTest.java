package com.sarality.app.view.list.test;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
    LinearLayout rowView = new LinearLayout(context);
    View view = new View(context);
    view.setId(viewId);
    ((ViewGroup) rowView).addView(view);

    rowViewCache.cacheViewWithId(rowView, viewId);
    assertEquals(view, rowViewCache.getViewById(rowView, viewId));
  }

  public void testGetViewId_withNoCache() {
    int viewIdCached = 1234;
    int viewIdNotCached = 5678;
    ListRowViewCache rowViewCache = new ListRowViewCache();
    LinearLayout rowView = new LinearLayout(context);
    View view = new View(context);
    view.setId(viewIdCached);
    ((ViewGroup) rowView).addView(view);

    // Cache only one vieId-View
    rowViewCache.cacheViewWithId(rowView, viewIdCached);

    // Should return view from rowView rather than cache
    assertEquals(null, rowViewCache.getViewById(view, viewIdNotCached));
  }
}
