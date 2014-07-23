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
    } catch (IllegalArgumentException e) {
      // Do nothing
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
