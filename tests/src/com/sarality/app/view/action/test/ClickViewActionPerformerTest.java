package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.view.View;

import com.sarality.app.view.action.ClickViewActionPerformer;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Tests for {@link ClickViewActionPerformer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ClickActionPerformerTest extends TestCase {

  @Test
  public void testSetupListener() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(1234);

    ClickViewActionPerformer clickAction = new ClickViewActionPerformer(action);
    clickAction.setupListener(view);

    Mockito.verify(view, Mockito.times(1)).setOnClickListener(clickAction);
  }

  @Test
  public void testSetupListener_InvalidArguments() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(5678);

    ClickViewActionPerformer clickAction = new ClickViewActionPerformer(action);
    try {
      clickAction.setupListener(view);
      fail("Exception should be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Trying to setup listener on view with Id 5678 while the action specifies the view id 1234",
          e.getMessage());
    }
    Mockito.verify(view, Mockito.times(0)).setOnClickListener(clickAction);
  }

  @Test
  public void testClickActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    ClickViewActionPerformer clickAction = new ClickViewActionPerformer(action);
    assertNotNull(clickAction);
  }

  @Test
  public void testOnClick() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    when(action.performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class), Mockito.any(ViewDetail.class)))
        .thenReturn(true);

    ClickViewActionPerformer clickAction = new ClickViewActionPerformer(action);
    clickAction.onClick(view);
    Mockito.verify(action, Mockito.times(1)).performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class),
        Mockito.any(ViewDetail.class));
  }
}
