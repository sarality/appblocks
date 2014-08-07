package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import android.view.View;

import com.sarality.app.view.action.BaseActionPerformer;
import com.sarality.app.view.action.ViewAction;

/**
 * Tests for {@link BaseActionPerformer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class BaseActionPerformerTest extends BaseUnitTest {

  public void testBaseActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    BaseActionPerformer performer = new TestActionPerformer(action);
    assertNotNull(performer);
  }

  public void testIsValidListenerView() {
    ViewAction action = mock(ViewAction.class);
    BaseActionPerformer performer = new TestActionPerformer(action);
    View view = new View(context);
    view.setId(1234);

    // stubbing
    when(action.getViewId()).thenReturn(1234);

    assertEquals(performer.isValidListenerView(view), true);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    view.setId(5678);

    try {
      performer.isValidListenerView(view);
      fail("Exception should be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Trying to setup listener on view with Id 5678 while the action specifies the view id 1234",
          e.getMessage());
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
