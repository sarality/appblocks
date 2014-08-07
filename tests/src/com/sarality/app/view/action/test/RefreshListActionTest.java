package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.mockito.Mockito;

import android.view.View;

import com.sarality.app.view.action.RefreshListAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.list.ListComponent;

/**
 * Tests for {@link RefreshListAction}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class RefreshListActionTest extends TestCase {

  public void testDoAction() {
    View view = mock(View.class);
    int viewId = 1234;
    TriggerType triggerType = TriggerType.CLICK;

    when(view.getId()).thenReturn(viewId);
    @SuppressWarnings("unchecked")
    ListComponent<String> component = (ListComponent<String>) mock(ListComponent.class);
    RefreshListAction<String> refreshAction = new RefreshListAction<String>(viewId, triggerType, component);

    assertEquals(true, refreshAction.doAction(view, null, null));
    Mockito.verify(component, Mockito.times(1)).refresh();
  }

  public void testLogEventAction() {
    int viewId = 1234;
    TriggerType triggerType = TriggerType.CLICK;
    @SuppressWarnings("unchecked")
    ListComponent<String> component = (ListComponent<String>) mock(ListComponent.class);
    RefreshListAction<String> refreshAction = new RefreshListAction<String>(viewId, triggerType, component);

    assertNotNull(refreshAction);
    assertEquals(viewId, refreshAction.getViewId());
    assertEquals(triggerType, refreshAction.getTriggerType());
  }
}
