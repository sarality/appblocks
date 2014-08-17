package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.view.MotionEvent;
import android.view.View;

import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewActionTrigger;

/**
 * Tests for {@link ViewActionTrigger}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ViewActionTriggerTest extends TestCase {

  @Test
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
