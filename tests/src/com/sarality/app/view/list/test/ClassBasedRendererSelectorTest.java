package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;

import com.sarality.app.view.action.test.BaseUnitTest;
import com.sarality.app.view.list.ClassBasedRendererSelector;
import com.sarality.app.view.list.ListRowRenderer;

/**
 * Tests for {@link ClassBasedRendererSelector}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ClassBasedRendererSelectorTest extends BaseUnitTest {

  public void testRegister_StringClass() {
    ClassBasedRendererSelector<String> selector = new ClassBasedRendererSelector<String>();
    assertNotNull(selector);

    assertEquals(selector, selector.register(String.class, null));
    assertEquals(null, selector.select("Test"));
  }

  public void testRegister_IntegerClass() {
    ClassBasedRendererSelector<Integer> selector = new ClassBasedRendererSelector<Integer>();
    assertNotNull(selector);
    @SuppressWarnings("unchecked")
    ListRowRenderer<Integer> rowRenderer = mock(ListRowRenderer.class);
    assertEquals(selector, selector.register(Integer.class, rowRenderer));
    assertEquals(rowRenderer, selector.select(1234));
  }

  public void testSelect_WithNoRegister() {
    ClassBasedRendererSelector<Integer> selector = new ClassBasedRendererSelector<Integer>();
    assertNotNull(selector);
    try {
      selector.select(1234);
    } catch (IllegalStateException e) {
      // Expected exception do nothing
    } catch (Exception e) {
      // for all others fail test case
      fail("Invalid Exception");
      e.printStackTrace();
    }
  }
}
