package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import android.view.View;

import com.sarality.app.view.action.ClickActionPerformer;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Tests for {@link ClickActionPerformer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ClickActionPerformerTest extends BaseUnitTest {

  public void testSetupListener() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(1234);

    ClickActionPerformer clickAction = new ClickActionPerformer(action);
    clickAction.setupListener(view);

    Mockito.verify(view, Mockito.times(1)).setOnClickListener(clickAction);
  }

  public void testSetupListener_InvalidArguments() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(5678);

    ClickActionPerformer clickAction = new ClickActionPerformer(action);
    try {
      clickAction.setupListener(view);
    } catch (IllegalArgumentException e) {
      // Do nothing
    }
    Mockito.verify(view, Mockito.times(0)).setOnClickListener(clickAction);
  }

  public void testClickActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    ClickActionPerformer clickAction = new ClickActionPerformer(action);
    assertNotNull(clickAction);
  }

  public void testOnClick() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    when(action.performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class), Mockito.any(ViewDetail.class)))
        .thenReturn(true);

    ClickActionPerformer clickAction = new ClickActionPerformer(action);
    clickAction.onClick(view);
    Mockito.verify(action, Mockito.times(1)).performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class),
        Mockito.any(ViewDetail.class));
  }

}
