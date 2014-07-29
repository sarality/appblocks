package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import android.view.MotionEvent;
import android.view.View;

import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewActionTrigger;

/**
 * Tests for {@link ViewActionTrigger}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ViewActionTriggerTest extends BaseUnitTest {

  public void testViewActionTrigger() {
    View view = mock(View.class);
    TriggerType triggerType = TriggerType.TOUCH;
    MotionEvent event = MotionEvent.obtain(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    ViewActionTrigger actionTrigger = new ViewActionTrigger(view, triggerType, event);

    assertNotNull(actionTrigger);
    assertEquals(event, actionTrigger.getMotionEvent());
    assertEquals(triggerType, actionTrigger.getTriggerType());
    assertEquals(view, actionTrigger.View());
  }
}
