package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.view.list.ClassBasedRendererSelector;
import com.sarality.app.view.list.ListRowRenderer;

/**
 * Tests for {@link ClassBasedRendererSelector}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ClassBasedRendererSelectorTest extends TestCase {

  @Test
  public void testRegister_StringClass() {
    ClassBasedRendererSelector<String> selector = new ClassBasedRendererSelector<String>();
    assertNotNull(selector);

    assertEquals(selector, selector.register(String.class, null));
    assertEquals(null, selector.select("Test"));
  }

  @Test
  public void testRegister_IntegerClass() {
    ClassBasedRendererSelector<Integer> selector = new ClassBasedRendererSelector<Integer>();
    assertNotNull(selector);
    @SuppressWarnings("unchecked")
    ListRowRenderer<Integer> rowRenderer = mock(ListRowRenderer.class);
    assertEquals(selector, selector.register(Integer.class, rowRenderer));
    assertEquals(rowRenderer, selector.select(1234));
  }

  @Test
  public void testSelect_WithNoRegister() {
    ClassBasedRendererSelector<Integer> selector = new ClassBasedRendererSelector<Integer>();
    assertNotNull(selector);
    try {
      selector.select(1234);
      fail("Exception should be thrown");
    } catch (IllegalStateException e) {
      assertEquals("No Renderer registered for class java.lang.Integer", e.getMessage());
    } catch (Exception e) {
      fail("Invalid Exception");
    }
  }
}
