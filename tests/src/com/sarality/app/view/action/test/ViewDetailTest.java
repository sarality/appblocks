package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;
import android.view.View;

import com.sarality.app.view.action.ViewDetail;

/**
 * Tests for {@link ViewDetail}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ViewDetailTest extends TestCase {

  public void testViewDetail() {
    View view = mock(View.class);
    View parent = mock(View.class);

    ViewDetail detail = new ViewDetail(view, parent);

    assertNotNull(detail);
    assertEquals(view, detail.getView());
    assertEquals(parent, detail.getParentView());
  }
}
