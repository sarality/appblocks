package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;

import org.mockito.Mockito;

import android.view.View;

import com.sarality.app.view.action.SupressEventAction;
import com.sarality.app.view.action.TriggerType;

/**
 * Tests for {@link SupressEventAction}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class SupressEventActionTest extends BaseUnitTest {

  public void testDoAction() {
    View view = mock(View.class);
    int viewId = 1234;
    TriggerType triggerType = TriggerType.CLICK;

    SupressEventAction eventAction = new SupressEventAction(viewId, triggerType);
    assertEquals(true, eventAction.doAction(view, null, null));
    Mockito.verifyZeroInteractions(view);
  }

  public void testLogEventAction() {
    int viewId = 1234;
    TriggerType triggerType = TriggerType.CLICK;
    SupressEventAction eventAction = new SupressEventAction(viewId, triggerType);

    assertNotNull(eventAction);
    assertEquals(viewId, eventAction.getViewId());
    assertEquals(triggerType, eventAction.getTriggerType());
  }

}
