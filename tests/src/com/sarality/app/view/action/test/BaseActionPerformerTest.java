package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import android.view.View;

import com.sarality.app.view.action.BaseActionPerformer;
import com.sarality.app.view.action.ViewAction;

/**
 * Tests for {@link BaseActionPerformer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class BaseActionPerformerTest extends TestCase {

  public void testBaseActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    BaseActionPerformer performer = new TestActionPerformer(action);
    assertNotNull(performer);
  }

  public void testIsValidListenerView() {
    ViewAction action = mock(ViewAction.class);
    BaseActionPerformer performer = new TestActionPerformer(action);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(1234);

    assertEquals(performer.isValidListenerView(view), true);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(5678);

    try {
      performer.isValidListenerView(view);
      fail("Exception should be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Trying to setup listener on view with Id 5678 while the action specifies the view id 1234", e.getMessage());
    }
  }
}

class TestActionPerformer extends BaseActionPerformer {

  public TestActionPerformer(ViewAction action) {
    super(action);
  }

  @Override
  public void setupListener(View view) {
  }

}
