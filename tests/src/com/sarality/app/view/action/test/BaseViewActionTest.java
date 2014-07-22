package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InOrder;
import org.mockito.Mockito;

import android.test.MoreAsserts;
import android.view.View;

import com.sarality.app.view.action.BaseViewAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Tests for {@link BaseViewAction}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class BaseViewActionTest extends AppblockActivityTest {

  public void testBaseViewAction() {
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    assertNotNull(action);
  }

  public void testGetViewId() {
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    assertEquals(Integer.valueOf(action.getViewId()), Integer.valueOf(1234));
  }

  public void testGetTriggerType() {
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    assertSame(action.getTriggerType(), TriggerType.CLICK);
  }

  public void testRegisterBeforeExecutionAction() {
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    ViewAction beforeAction = mock(ViewAction.class);
    action.registerBeforeExecutionAction(beforeAction);
    MoreAsserts.assertContentsInOrder(action.getActions(), beforeAction);
  }

  public void testRegisterOnSuccessAction() {
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    ViewAction onSuccessAction = mock(ViewAction.class);
    action.registerOnSuccessAction(onSuccessAction);
    MoreAsserts.assertContentsInOrder(action.getActions(), onSuccessAction);
  }

  public void testRegisterOnFailureAction() {
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    ViewAction onFailureAction = mock(ViewAction.class);
    action.registerOnFailureAction(onFailureAction);
    MoreAsserts.assertContentsInOrder(action.getActions(), onFailureAction);
  }

  public void testGetActions() {
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    ViewAction onFailureAction = mock(ViewAction.class);
    action.registerOnFailureAction(onFailureAction);
    ViewAction onSuccessAction = mock(ViewAction.class);
    action.registerOnSuccessAction(onSuccessAction);
    ViewAction beforeAction = mock(ViewAction.class);
    action.registerBeforeExecutionAction(beforeAction);
    MoreAsserts.assertContentsInAnyOrder(action.getActions(), beforeAction, onSuccessAction, onFailureAction);
  }

  public void testPerformAction() {
    // Mock ViewAction Trigger
    ViewActionTrigger actionTrigger = mock(ViewActionTrigger.class);

    // FAILURE Actions
    ViewAction onFailureAction = mock(ViewAction.class);
    when(onFailureAction.performAction(null, actionTrigger, null)).thenReturn(false);
    when(onFailureAction.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(onFailureAction.getViewId()).thenReturn(1234);

    // SUCCESS Action
    ViewAction onSuccessAction = mock(ViewAction.class);
    when(onSuccessAction.performAction(null, actionTrigger, null)).thenReturn(false);
    when(onSuccessAction.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(onSuccessAction.getViewId()).thenReturn(1234);

    // BEFORE Action
    ViewAction beforeAction = mock(ViewAction.class);
    when(beforeAction.performAction(null, actionTrigger, null)).thenReturn(false);
    when(beforeAction.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(beforeAction.getViewId()).thenReturn(1234);

    // Actual Test
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    action.registerOnFailureAction(onFailureAction);
    action.registerOnSuccessAction(onSuccessAction);
    action.registerBeforeExecutionAction(beforeAction);

    boolean result = action.performAction(null, actionTrigger, null);

    // create inOrder object passing any mocks that need to be verified in order
    InOrder inOrder = Mockito.inOrder(beforeAction, onSuccessAction);
    inOrder.verify(beforeAction).performAction((View) Mockito.eq(null), (ViewActionTrigger) Mockito.anyObject(),
        (ViewDetail) Mockito.eq(null));
    inOrder.verify(onSuccessAction).performAction((View) Mockito.eq(null), (ViewActionTrigger) Mockito.anyObject(),
        (ViewDetail) Mockito.eq(null));
    Mockito.verifyZeroInteractions(onFailureAction);

    assertEquals(result, true);
  }

  public void testFailedAction() {
    // Mock ViewAction Trigger
    ViewActionTrigger actionTrigger = mock(ViewActionTrigger.class);

    // FAILURE Actions
    ViewAction onFailureAction = mock(ViewAction.class);
    when(
        onFailureAction.performAction((View) Mockito.eq(null), (ViewActionTrigger) Mockito.anyObject(),
            (ViewDetail) Mockito.eq(null))).thenReturn(false);
    when(onFailureAction.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(onFailureAction.getViewId()).thenReturn(1234);

    // BEFORE Action
    ViewAction beforeAction = mock(ViewAction.class);
    when(
        beforeAction.performAction((View) Mockito.eq(null), (ViewActionTrigger) Mockito.anyObject(),
            (ViewDetail) Mockito.eq(null))).thenThrow(new RuntimeException("Test for Failure"));
    when(beforeAction.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(beforeAction.getViewId()).thenReturn(1234);

    // Actual Test
    TestDummyBaseViewAction action = new TestDummyBaseViewAction(1234, TriggerType.CLICK);
    action.registerOnFailureAction(onFailureAction);
    action.registerBeforeExecutionAction(beforeAction);

    boolean result = action.performAction(null, actionTrigger, null);

    // create inOrder object passing any mocks that need to be verified in order
    InOrder inOrder = Mockito.inOrder(beforeAction, onFailureAction);
    inOrder.verify(beforeAction).performAction((View) Mockito.eq(null), (ViewActionTrigger) Mockito.anyObject(),
        (ViewDetail) Mockito.eq(null));
    inOrder.verify(onFailureAction).performAction((View) Mockito.eq(null), (ViewActionTrigger) Mockito.anyObject(),
        (ViewDetail) Mockito.eq(null));

    assertEquals(result, false);

  }
}

class TestDummyBaseViewAction extends BaseViewAction {

  public TestDummyBaseViewAction(int viewId, TriggerType triggerType) {
    super(viewId, triggerType);
  }

  @Override
  public boolean doAction(View view, ViewActionTrigger actionDetail, ViewDetail viewDetail) {
    return true;
  }

}
