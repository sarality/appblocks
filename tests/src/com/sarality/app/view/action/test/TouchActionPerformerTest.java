package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.view.MotionEvent;
import android.view.View;

import com.sarality.app.view.action.TouchActionPerformer;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Tests for {@link TouchActionPerformer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class TouchActionPerformerTest extends TestCase {

  @Test
  public void testSetupListener() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(1234);

    TouchActionPerformer clickAction = new TouchActionPerformer(action);
    clickAction.setupListener(view);

    Mockito.verify(view, Mockito.times(1)).setOnTouchListener(clickAction);
  }

  @Test
  public void testSetupListener_InvalidArguments() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(5678);

    TouchActionPerformer clickAction = new TouchActionPerformer(action);
    try {
      clickAction.setupListener(view);
      fail("Exception should be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Trying to setup listener on view with Id 5678 while the action specifies the view id 1234",
          e.getMessage());
    }
    Mockito.verify(view, Mockito.times(0)).setOnTouchListener(clickAction);
  }

  @Test
  public void testClickActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    TouchActionPerformer clickAction = new TouchActionPerformer(action);
    assertNotNull(clickAction);
  }

  @Test
  public void testOnClick_TouchDown() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);
    MotionEvent event = MotionEvent.obtain(100, 1000, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    event.setAction(MotionEvent.ACTION_DOWN);

    when(action.performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class), Mockito.any(ViewDetail.class)))
        .thenReturn(true);
    when(action.getTriggerType()).thenReturn(TriggerType.TOUCH_DOWN);

    TouchActionPerformer clickAction = new TouchActionPerformer(action);
    assertEquals(true, clickAction.onTouch(view, event));
    Mockito.verify(action, Mockito.times(1)).performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class),
        Mockito.any(ViewDetail.class));
  }

  @Test
  public void testOnClick_Touchup() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);
    MotionEvent event = MotionEvent.obtain(100, 1000, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    event.setAction(MotionEvent.ACTION_UP);

    when(action.performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class), Mockito.any(ViewDetail.class)))
        .thenReturn(true);
    when(action.getTriggerType()).thenReturn(TriggerType.TOUCH_UP);

    TouchActionPerformer clickAction = new TouchActionPerformer(action);
    assertEquals(true, clickAction.onTouch(view, event));
    Mockito.verify(action, Mockito.times(1)).performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class),
        Mockito.any(ViewDetail.class));
  }

  @Test
  public void testOnClick_InvalidMotionEvent() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);
    MotionEvent event = MotionEvent.obtain(100, 1000, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    event.setAction(MotionEvent.ACTION_CANCEL);

    when(action.performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class), Mockito.any(ViewDetail.class)))
        .thenReturn(true);
    when(action.getTriggerType()).thenReturn(TriggerType.TOUCH_UP);

    TouchActionPerformer clickAction = new TouchActionPerformer(action);
    assertEquals(false, clickAction.onTouch(view, event));
    Mockito.verify(action, Mockito.times(0)).performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class),
        Mockito.any(ViewDetail.class));
  }
}
