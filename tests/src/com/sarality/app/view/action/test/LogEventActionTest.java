package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import android.view.View;

import com.sarality.app.view.action.LogEventAction;
import com.sarality.app.view.action.TriggerType;

/**
 * Tests for {@link LogEventAction}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class LogEventActionTest extends TestCase {

  public void testDoAction() {
    View view = mock(View.class);
    int viewId = 1234;
    TriggerType triggerType = TriggerType.CLICK;

    when(view.getId()).thenReturn(viewId);
    LogEventAction logEventAction = new LogEventAction(viewId, triggerType);

    assertEquals(true, logEventAction.doAction(view, null, null));
  }

  public void testLogEventAction() {
    int viewId = 1234;
    TriggerType triggerType = TriggerType.CLICK;
    LogEventAction logEventAction = new LogEventAction(viewId, triggerType);

    assertNotNull(logEventAction);
    assertEquals(viewId, logEventAction.getViewId());
    assertEquals(triggerType, logEventAction.getTriggerType());
  }

}
