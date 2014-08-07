package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.mockito.InOrder;
import org.mockito.Mockito;

import com.sarality.app.view.action.CompositeViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;

/**
 * Tests for {@link CompositeViewAction}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class CompositeViewActionTest extends TestCase {

  public void testCompositeViewAction() {
    CompositeViewAction viewAction = new CompositeViewAction(1, TriggerType.CLICK);
    assertNotNull(viewAction);
    assertEquals(TriggerType.CLICK, viewAction.getTriggerType());
    assertEquals(1, viewAction.getViewId());
  }

  private ViewAction createAction(int viewId, TriggerType triggerType) {
    ViewAction action = mock(ViewAction.class);
    when(action.getViewId()).thenReturn(viewId);
    when(action.getTriggerType()).thenReturn(triggerType);
    when(action.performAction(null, null, null)).thenReturn(true);
    return action;
  }

  public void testRegisterAction() {
    CompositeViewAction viewAction = new CompositeViewAction(1, TriggerType.CLICK);
    ViewAction clickAction1 = createAction(viewAction.getViewId(), viewAction.getTriggerType());
    ViewAction clickAction2 = createAction(viewAction.getViewId(), viewAction.getTriggerType());
    ViewAction nullAction = mock(ViewAction.class);

    // create inOrder object passing any mocks that need to be verified in order
    InOrder inOrder = Mockito.inOrder(clickAction1, clickAction2);

    viewAction.registerAction(clickAction1);
    viewAction.registerAction(clickAction2);
    boolean result = viewAction.doAction(null, null, null);

    assertEquals(result, true);
    inOrder.verify(clickAction1).performAction(null, null, null);
    inOrder.verify(clickAction2).performAction(null, null, null);
    Mockito.verifyZeroInteractions(nullAction);
  }

  public void testRegisterAction_IllegalArgument() {
    CompositeViewAction viewAction = new CompositeViewAction(1, TriggerType.CLICK);
    ViewAction clickActionView2 = createAction(2, TriggerType.CLICK);
    ViewAction touchActionView1 = createAction(1, TriggerType.TOUCH);

    try {
      viewAction.registerAction(clickActionView2);
      fail("Exception should be thrown");
      viewAction.registerAction(touchActionView1);
    } catch (IllegalArgumentException e) {
      assertEquals(
          "Invalid Action , cannot add TriggerType=CLICK for CompositeView with Trigger Type= CLICK and View Id =2 CompositeView viewId= 1",
          e.getMessage());
    } catch (Exception e) {
      fail("Exception other than expected IllegalArgumentException thrown");
    }

    // clickActionView2 should not get registered as the compositeAction View and action view do not match
    Mockito.verify(clickActionView2, Mockito.times(2)).getViewId();
    // Exception should be thrown before registration for this item happens
    Mockito.verifyZeroInteractions(touchActionView1);

    ViewAction clickActionView1 = createAction(1, TriggerType.CLICK);

    try {
      viewAction.registerAction(clickActionView1);
      viewAction.registerAction(touchActionView1);
      fail("Exception should not be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals(
          "Invalid Action , cannot add TriggerType=TOUCH for CompositeView with Trigger Type= CLICK and View Id =1 CompositeView viewId= 1",
          e.getMessage());
    } catch (Exception e) {
      fail("Exception other than expected IllegalArgumentException thrown");
    }
    // clickActionView1 gets registered
    // touchActionView1 should not get registered
    // IllegalArgument exception would be thrown
    Mockito.verify(clickActionView1, Mockito.times(1)).getViewId();
    Mockito.verify(touchActionView1, Mockito.times(2)).getViewId();
  }
}
