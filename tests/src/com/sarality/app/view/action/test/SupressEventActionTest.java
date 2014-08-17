package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.view.View;

import com.sarality.app.view.action.SupressEventAction;
import com.sarality.app.view.action.TriggerType;

/**
 * Tests for {@link SupressEventAction}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class SupressEventActionTest extends TestCase {

  @Test
  public void testDoAction() {
    View view = mock(View.class);
    int viewId = 1234;
    TriggerType triggerType = TriggerType.CLICK;

    SupressEventAction eventAction = new SupressEventAction(viewId, triggerType);
    assertEquals(true, eventAction.doAction(view, null, null));
    Mockito.verifyZeroInteractions(view);
  }

  @Test
  public void testLogEventAction() {
    int viewId = 1234;
    TriggerType triggerType = TriggerType.CLICK;
    SupressEventAction eventAction = new SupressEventAction(viewId, triggerType);

    assertNotNull(eventAction);
    assertEquals(viewId, eventAction.getViewId());
    assertEquals(triggerType, eventAction.getTriggerType());
  }
}
