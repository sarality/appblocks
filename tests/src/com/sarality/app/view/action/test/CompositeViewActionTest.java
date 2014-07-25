package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InOrder;
import org.mockito.Mockito;

import android.util.Log;

import com.sarality.app.view.action.CompositeViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;

/**
 * Tests for {@link CompositeViewAction}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class CompositeViewActionTest extends BaseUnitTest {

  private static final String TAG = null;

  public void testCompositeViewAction() {
    CompositeViewAction viewAction = new CompositeViewAction(1, TriggerType.CLICK);
    assertNotNull(viewAction);
    viewAction = new CompositeViewAction(1, TriggerType.CLICK_LIST_ITEM);
    assertNotNull(viewAction);
    viewAction = new CompositeViewAction(1, TriggerType.LONG_CLICK);
    assertNotNull(viewAction);
    viewAction = new CompositeViewAction(1, TriggerType.LONG_CLICK_LIST_ITEM);
    assertNotNull(viewAction);
    viewAction = new CompositeViewAction(1, TriggerType.TOUCH);
    assertNotNull(viewAction);
    viewAction = new CompositeViewAction(1, TriggerType.TOUCH_DOWN);
    assertNotNull(viewAction);
    viewAction = new CompositeViewAction(1, TriggerType.TOUCH_UP);
    assertNotNull(viewAction);
  }

  public void testRegisterAction() {
    CompositeViewAction viewAction = new CompositeViewAction(1, TriggerType.CLICK);
    ViewAction action1 = mock(ViewAction.class);
    ViewAction action2 = mock(ViewAction.class);
    ViewAction action3 = mock(ViewAction.class);

    when(action1.getViewId()).thenReturn(1);
    when(action2.getViewId()).thenReturn(1);
    when(action1.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(action2.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(action2.performAction(null, null, null)).thenReturn(true);
    when(action1.performAction(null, null, null)).thenReturn(true);
    when(action2.performAction(null, null, null)).thenReturn(true);

    // create inOrder object passing any mocks that need to be verified in order
    InOrder inOrder = Mockito.inOrder(action1, action2);
 
    viewAction.registerAction(action1);
    viewAction.registerAction(action2);
    boolean result = viewAction.doAction(null, null, null);

    assertEquals(result, true);
    inOrder.verify(action1).performAction(null, null, null);
    inOrder.verify(action2).performAction(null, null, null);
    Mockito.verifyZeroInteractions(action3);
 }

  public void testRegisterAction_IllegalArgument() {
    CompositeViewAction viewAction = new CompositeViewAction(1, TriggerType.CLICK);
    ViewAction action1 = mock(ViewAction.class);
    ViewAction action2 = mock(ViewAction.class);

    when(action1.getViewId()).thenReturn(2);
    when(action2.getViewId()).thenReturn(1);
    when(action1.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(action2.getTriggerType()).thenReturn(TriggerType.TOUCH);

    try {
      viewAction.registerAction(action1);
      viewAction.registerAction(action2);
    } catch (IllegalArgumentException e) {
      Log.d(TAG,"Exception thrown...");
    }

    when(action1.getViewId()).thenReturn(1);
    
    try {
      viewAction.registerAction(action1);
      viewAction.registerAction(action2);
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    Mockito.verify(action1, Mockito.times(3)).getViewId();
    Mockito.verify(action2, Mockito.times(2)).getViewId();
  }
}
