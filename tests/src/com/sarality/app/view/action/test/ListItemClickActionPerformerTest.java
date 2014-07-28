package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import android.content.Context;
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
public class ListItemClickActionPerformerTest extends BaseUnitTest {

  public void testSetupListener() {
    Context context = getInstrumentation().getTargetContext();
    ViewAction action = mock(ViewAction.class);
    ListView listView = new ListView(context);
    listView.setId(1234);

    // stubbing
    when(action.getViewId()).thenReturn(1234);

    try {
      ListItemClickActionPerformer actionPerformer = new ListItemClickActionPerformer(action);
      actionPerformer.setupListener(listView);
    } catch (IllegalArgumentException e) {
      // Do nothing;
    }
  }

  public void testSetupListener_WithBadArguments() {
    Context context = getInstrumentation().getTargetContext();
    ViewAction action = mock(ViewAction.class);
    ListView listView = new ListView(context);
    listView.setId(4567);

    // stubbing
    when(action.getViewId()).thenReturn(1234);

    try {
      ListItemClickActionPerformer actionPerformer = new ListItemClickActionPerformer(action);
      actionPerformer.setupListener(listView);
    } catch (IllegalArgumentException e) {
      // Do nothing;
    }
  }

  public void testListItemClickActionPerformer() {
    ViewAction action = mock(ViewAction.class);
    ListItemClickActionPerformer actionPerformer = new ListItemClickActionPerformer(action);
    assertNotNull(actionPerformer);
  }

  public void testOnItemClick() {
    Context context = getInstrumentation().getTargetContext();
    ViewAction action = mock(ViewAction.class);
    View view = mock(View.class);
    ListView listView = new ListView(context);

    when(action.performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class), Mockito.any(ViewDetail.class)))
        .thenReturn(true);

    ListItemClickActionPerformer clickAction = new ListItemClickActionPerformer(action);
    clickAction.onItemClick(listView, view, 1, 0);

    Mockito.verify(action, Mockito.times(1)).performAction(Mockito.eq(view), Mockito.any(ViewActionTrigger.class),
        Mockito.any(ViewDetail.class));
  }

}
