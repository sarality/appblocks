package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.action.ComponentActionManager;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewAction;

/**
 * Tests for {@link ComponentActionManager}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ComponentActionManagerTest extends TestCase {
  Context context;

  private ViewAction view1Action1;
  private ViewAction listView2Action1;
  private ViewAction view1Action2;
  private ViewAction listView2Action2;
  private ViewAction view3Action1;
  private ViewAction view3Action2;
  private ViewAction view3Action3;
  private ViewAction view1Action3;
  List<ViewAction> actionList;

  @Before
  public void setUp() {
    // view1Action1, view1Action2, view1Action3 on view1
    // listViewAction1, listViewAction2 on listView
    // view2Action1, view2Action2, view2Action3 on view3

    view1Action1 = createMockAction(1, TriggerType.CLICK);
    listView2Action1 = createMockAction(2, TriggerType.CLICK_LIST_ITEM);
    view1Action2 = createMockAction(1, TriggerType.LONG_CLICK);
    listView2Action2 = createMockAction(2, TriggerType.LONG_CLICK_LIST_ITEM);
    view3Action1 = createMockAction(3, TriggerType.TOUCH);
    view3Action2 = createMockAction(3, TriggerType.TOUCH_DOWN);
    view3Action3 = createMockAction(3, TriggerType.TOUCH_UP);
    view1Action3 = createMockAction(1, TriggerType.CLICK);

    context = Robolectric.application.getApplicationContext();

  }

  private ViewAction createMockAction(int viewId, TriggerType trigger) {
    ViewAction action = mock(ViewAction.class);
    when(action.getTriggerType()).thenReturn(trigger);
    when(action.getViewId()).thenReturn(viewId);
    return action;
  }

  private void setupActionList() {
    actionList = Lists.of(view1Action1, listView2Action1, view1Action2, listView2Action2, view3Action1, view3Action2,
        view3Action3, view1Action3);
  }

  @Test
  public void testComponentActionManager() {
    setupActionList();
    ComponentActionManager actionManager = new ComponentActionManager(actionList);
    assertNotNull(actionManager);
  }

  @Test
  public void testSetupActions() {
    // LAYOUT
    // VIEW with ID 1
    // LISTVIEW with ID 2
    // VIEW with ID 3
    LinearLayout layout = new LinearLayout(context);
    View view1 = new View(context);
    view1.setId(1);
    View view3 = new View(context);
    view3.setId(3);
    ListView listView = new ListView(context);
    listView.setId(2);

    setupActionList();
    layout.addView(view1);
    layout.addView(listView);
    layout.addView(view3);

    ComponentActionManager actionManager = new ComponentActionManager(actionList);
    // No exceptions should be thrown
    actionManager.setupActions(layout);
  }

  @Test
  public void testSetUpActions_Exception() {
    LinearLayout layout = new LinearLayout(context);
    View view1 = mock(View.class);
    Mockito.when(view1.getId()).thenReturn(13);
    layout.addView(view1);

    actionList = Lists.of(view1Action1);

    ComponentActionManager actionManager = new ComponentActionManager(actionList);
    try {
      actionManager.setupActions(layout);
      fail("Exception should be thrown");
    } catch (IllegalStateException e) {
      assertEquals("Invalid Configuration for CLICK Event. No View with Id 1 found in row ffffffff", e.getMessage());
    }
  }
}
