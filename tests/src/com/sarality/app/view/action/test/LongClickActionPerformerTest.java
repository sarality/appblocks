package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.mockito.Mockito;

import android.view.View;

import com.sarality.app.view.action.LongClickActionPerformer;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Tests for {@link LongClickActionPerformer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class LongClickActionPerformerTest extends TestCase {

  public void testSetupListener() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(1234);

    LongClickActionPerformer clickAction = new LongClickActionPerformer(action);
    clickAction.setupListener(view);

    Mockito.verify(view, Mockito.times(1)).setOnLongClickListener(clickAction);
  }

  public void testSetupListener_InvalidArguments() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(5678);

    LongClickActionPerformer clickAction = new LongClickActionPerformer(action);
    try {
      clickAction.setupListener(view);
      fail("Exception should be thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Trying to setup listener on view with Id 5678 while the action specifies the view id 1234",
          e.getMessage());
    }
    Mockito.verify(view, Mockito.times(0)).setOnLongClickListener(clickAction);
  }

  public void testClickActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    LongClickActionPerformer clickAction = new LongClickActionPerformer(action);
    assertNotNull(clickAction);
  }

  public void testOnClick() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    when(action.performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class), Mockito.any(ViewDetail.class)))
        .thenReturn(true);

    LongClickActionPerformer clickAction = new LongClickActionPerformer(action);
    clickAction.onLongClick(view);
    Mockito.verify(action, Mockito.times(1)).performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class),
        Mockito.any(ViewDetail.class));
  }

}
