package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import org.mockito.Mockito;

import android.view.View;

import com.sarality.app.view.list.CompositeListRowRenderer;
import com.sarality.app.view.list.ListRowRenderer;
import com.sarality.app.view.list.ListRowRendererSelector;

/**
 * Tests for {@link CompositeListRowRenderer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class CompositeListRowRendererTest extends TestCase {

  public void testCompositeListRowRenderer() {
    CompositeListRowRenderer<String> rowRenderer = new CompositeListRowRenderer<String>(null);
    assertNotNull(rowRenderer);
  }

  @SuppressWarnings("unchecked")
  public void testGetRowLayout() {
    ListRowRendererSelector<String> selector = mock(ListRowRendererSelector.class);
    CompositeListRowRenderer<String> rowRenderer = new CompositeListRowRenderer<String>(selector);
    ListRowRenderer<String> renderer = mock(ListRowRenderer.class);
    
    Mockito.when(selector.select("1234")).thenReturn(renderer); 
    Mockito.when(renderer.getRowLayout("1234")).thenReturn(1234);
    
    assertEquals(1234,rowRenderer.getRowLayout("1234"));
  }

  @SuppressWarnings("unchecked")
  public void testPopulateViewCache() {
    ListRowRendererSelector<String> selector = mock(ListRowRendererSelector.class);
    CompositeListRowRenderer<String> rowRenderer = new CompositeListRowRenderer<String>(selector);
    ListRowRenderer<String> renderer = mock(ListRowRenderer.class);
    
    Mockito.when(selector.select("1234")).thenReturn(renderer); 
    
    rowRenderer.populateViewCache(null, null, "1234");
    Mockito.verify(renderer, Mockito.times(1)).populateViewCache(null, null, "1234");

    View view = mock(View.class);
    rowRenderer.populateViewCache(view, null, "1234");
    Mockito.verify(renderer, Mockito.times(1)).populateViewCache(view, null, "1234");
  }

  @SuppressWarnings("unchecked")
  public void testRender() {
    ListRowRendererSelector<String> selector = mock(ListRowRendererSelector.class);
    CompositeListRowRenderer<String> rowRenderer = new CompositeListRowRenderer<String>(selector);
    ListRowRenderer<String> renderer = mock(ListRowRenderer.class);
    
    Mockito.when(selector.select("1234")).thenReturn(renderer); 
    
    View view = mock(View.class);
    rowRenderer.populateViewCache(view, null, "1234");
    Mockito.verify(renderer, Mockito.times(1)).populateViewCache(view, null, "1234");
  }

  @SuppressWarnings("unchecked")
  public void testSetupActions() {
    ListRowRendererSelector<String> selector = mock(ListRowRendererSelector.class);
    CompositeListRowRenderer<String> rowRenderer = new CompositeListRowRenderer<String>(selector);
    ListRowRenderer<String> renderer = mock(ListRowRenderer.class);
    
    Mockito.when(selector.select("1234")).thenReturn(renderer); 
    
    View view = mock(View.class);
    rowRenderer.setupActions(view, null, "1234", null);
    Mockito.verify(renderer, Mockito.times(1)).setupActions(view, null, "1234",null);
  }

  @SuppressWarnings("unchecked")
  public void testSetAdditionalTagList() {
    int viewId = 1234;
    ListRowRendererSelector<String> selector = mock(ListRowRendererSelector.class);
    CompositeListRowRenderer<String> rowRenderer = new CompositeListRowRenderer<String>(selector);
    ListRowRenderer<String> renderer = mock(ListRowRenderer.class);
    
    Mockito.when(selector.select("1234")).thenReturn(renderer); 
    
    rowRenderer.setAdditionalTagList(viewId, null, "1234");
    Mockito.verify(renderer, Mockito.times(1)).setAdditionalTagList(viewId, null, "1234");
  }

  @SuppressWarnings("unchecked")
  public void testGetRowLayout_IntegerClass() {
    ListRowRendererSelector<Integer> selector = mock(ListRowRendererSelector.class);
    CompositeListRowRenderer<Integer> rowRenderer = new CompositeListRowRenderer<Integer>(selector);
    ListRowRenderer<Integer> renderer = mock(ListRowRenderer.class);
    
    Mockito.when(selector.select(1234)).thenReturn(renderer); 
    Mockito.when(renderer.getRowLayout(1234)).thenReturn(1234);
    
    assertEquals(1234,rowRenderer.getRowLayout(1234));
  }
}
