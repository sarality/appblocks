package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.res.Resources;
import android.test.mock.MockContext;
import android.view.View;

import com.sarality.app.view.action.BaseViewActionPerformer;
import com.sarality.app.view.action.ViewAction;

/**
 * Tests for {@link BaseViewActionPerformer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class BaseActionPerformerTest extends TestCase {

  @Test
  public void testBaseActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    BaseViewActionPerformer performer = new TestActionPerformer(action);
    assertNotNull(performer);
  }

  @Test
  public void testIsValidListenerView() {
    ViewAction action = mock(ViewAction.class);
    BaseViewActionPerformer performer = new TestActionPerformer(action);
    View view = new View(Robolectric.application);
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

class TestActionPerformer extends BaseViewActionPerformer {

  public TestActionPerformer(ViewAction action) {
    super(action);
  }

  @Override
  public void setupListener(View view) {
  }

}

class TestContext extends MockContext{
  @Override
  public Resources getResources(){
    return mock(Resources.class);
  }
}

