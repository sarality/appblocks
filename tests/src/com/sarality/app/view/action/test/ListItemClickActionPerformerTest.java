package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.view.View;
import android.widget.ListView;

import com.sarality.app.view.action.ListItemClickActionPerformer;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.ViewActionTrigger;
import com.sarality.app.view.action.ViewDetail;

/**
 * Tests for {@link ListItemClickActionPerformer}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ListItemClickActionPerformerTest extends TestCase {
  
  @Test
  public void testSetupListener() {
    ViewAction action = mock(ViewAction.class);
    ListView listView = mock(ListView.class);
    Mockito.when(listView.getId()).thenReturn(1234);

    // stubbing
    when(action.getViewId()).thenReturn(1234);
    ListItemClickActionPerformer actionPerformer = new ListItemClickActionPerformer(action);
    actionPerformer.setupListener(listView);
    Mockito.verify(listView).setOnItemClickListener(actionPerformer);
  }

  @Test
  public void testSetupListener_WithBadArguments() {
    ViewAction action = mock(ViewAction.class);
    ListView listView = mock(ListView.class);
    Mockito.when(listView.getId()).thenReturn(4567);

    // stubbing
    when(action.getViewId()).thenReturn(1234);

    try {
      ListItemClickActionPerformer actionPerformer = new ListItemClickActionPerformer(action);
      actionPerformer.setupListener(listView);
    } catch (IllegalArgumentException e) {
      assertEquals("Trying to setup listener on view with Id 4567 while the action specifies " +
      		"the view id 1234", e.getMessage());
    }
  }

  @Test
  public void testListItemClickActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    ListItemClickActionPerformer actionPerformer = new ListItemClickActionPerformer(action);
    assertNotNull(actionPerformer);
  }

  @Test
  public void testOnItemClick() {
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);
    ListView listView = mock(ListView.class);

    when(action.performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class), Mockito.any(ViewDetail.class)))
        .thenReturn(true);

    ListItemClickActionPerformer clickAction = new ListItemClickActionPerformer(action);
    clickAction.onItemClick(listView, view, 1, 0);

    Mockito.verify(action, Mockito.times(1)).performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class),
        Mockito.any(ViewDetail.class));
  }
}
