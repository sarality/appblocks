package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import org.mockito.Mockito;

import android.util.SparseArray;
import android.view.View;

import com.sarality.app.view.action.ComponentActionManager;
import com.sarality.app.view.list.BaseListRowRenderer;
import com.sarality.app.view.list.ListRowViewCache;

/**
 * Tests for {@link BaseListRowRenderer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class BaseListRowRendererTest extends TestCase {

  public void testSetAdditionalTagList() {
    TestBaseListRowRenderer rowRenderer = new TestBaseListRowRenderer();
    int viewId = 1234;
    Object data = new Object();
    rowRenderer.setAdditionalTagList(viewId, data, null);
    SparseArray<Object> list = rowRenderer.getTagList();

    assertEquals(data, list.get(viewId));
    assertEquals(1, list.size());
  }

  public void testSetupActions() {
    TestBaseListRowRenderer rowRenderer = new TestBaseListRowRenderer();
    View rowView = mock(View.class);
    ListRowViewCache rowViewCache = null;
    String value = null;
    ComponentActionManager componentManager = mock(ComponentActionManager.class);
    rowRenderer.setupActions(rowView, rowViewCache, value, componentManager);
    Mockito.verify(componentManager, Mockito.times(1)).setupActions(rowView);
  }
}

class TestBaseListRowRenderer extends BaseListRowRenderer<String> {

  @Override
  public int getRowLayout(String value) {
    return 0;
  }

  @Override
  public void populateViewCache(View rowView, ListRowViewCache rowViewCache, String value) {
  }

  @Override
  public void render(View rowView, ListRowViewCache rowViewCache, String value) {
  }

  public SparseArray<Object> getTagList() {
    return tagList;
  }
}
