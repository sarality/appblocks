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
public class ClickActionPerformerTest extends AppblockActivityTest {

  public void testSetupListener() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    when(view.getId()).thenReturn(1234);

    ClickActionPerformer clickAction = new ClickActionPerformer(action);
    clickAction.setupListener(view);
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
  }

}
