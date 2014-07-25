package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import android.content.Context;
import android.view.View;
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
public class ComponentActionManagerTest extends BaseUnitTest {

  private ViewAction action1;
  private ViewAction action2;
  private ViewAction action3;
  private ViewAction action4;
  private ViewAction action5;
  private ViewAction action6;
  private ViewAction action7;
  private ViewAction action8;
  List<ViewAction> actionList;

  @Override
  public void setUp() {
    try {
      super.setUp();
    } catch (Exception e) {
      e.printStackTrace();
    }
    action1 = mock(ViewAction.class);
    action2 = mock(ViewAction.class);
    action3 = mock(ViewAction.class);
    action4 = mock(ViewAction.class);
    action5 = mock(ViewAction.class);
    action6 = mock(ViewAction.class);
    action7 = mock(ViewAction.class);
    action8 = mock(ViewAction.class);
  }

  private void setupActionList() {
    actionList = Lists.of(action1, action2, action3, action4, action5, action6, action7, action8);
  }

  private void setupTriggerType() {
    when(action1.getTriggerType()).thenReturn(TriggerType.CLICK);
    when(action2.getTriggerType()).thenReturn(TriggerType.CLICK_LIST_ITEM);
    when(action3.getTriggerType()).thenReturn(TriggerType.LONG_CLICK);
    when(action4.getTriggerType()).thenReturn(TriggerType.LONG_CLICK_LIST_ITEM);
    when(action5.getTriggerType()).thenReturn(TriggerType.TOUCH);
    when(action6.getTriggerType()).thenReturn(TriggerType.TOUCH_DOWN);
    when(action7.getTriggerType()).thenReturn(TriggerType.TOUCH_UP);
    when(action8.getTriggerType()).thenReturn(TriggerType.CLICK);
  }

  private void setupActionGetViewId() {
    // ACTION1, ACTION3, ACTION8 on view1
    // ACTION2, ACTION4 on listView
    // ACTION5, ACTION6, ACTION7 on view3

    when(action1.getViewId()).thenReturn(1);
    when(action2.getViewId()).thenReturn(2);
    when(action3.getViewId()).thenReturn(1);
    when(action4.getViewId()).thenReturn(2);
    when(action5.getViewId()).thenReturn(3);
    when(action6.getViewId()).thenReturn(3);
    when(action7.getViewId()).thenReturn(3);
    when(action8.getViewId()).thenReturn(1);
  }

  public void testComponentActionManager() {
    setupActionList();
    setupActionGetViewId();
    ComponentActionManager actionManager = new ComponentActionManager(actionList);
    assertNotNull(actionManager);
  }

  public void testSetupActions() {
    // LAYOUT
    // VIEW with ID 1
    // LISTVIEW with ID 2
    // VIEW with ID 3
    Context context = getInstrumentation().getTargetContext();
    View layout = mock(View.class);
    View view1 = new View(context);
    view1.setId(1);
    View view3 = new View(context);
    view3.setId(3);
    ListView listView = new ListView(context);
    listView.setId(2);

    setupActionList();
    setupActionGetViewId();
    setupTriggerType();

    when(layout.findViewById(1)).thenReturn(view1);
    when(layout.findViewById(2)).thenReturn(listView);
    when(layout.findViewById(3)).thenReturn(view3);

    ComponentActionManager actionManager = new ComponentActionManager(actionList);
    actionManager.setupActions(layout);
  }
  
  public void testSetUpActions_Exception(){
    Context context = getInstrumentation().getTargetContext();
    View layout = mock(View.class);
    View view1 = new View(context);
    view1.setId(13);

    actionList = Lists.of(action1);
    setupActionGetViewId();
    setupTriggerType();

    when(layout.findViewById(1)).thenReturn(view1);
    
    ComponentActionManager actionManager = new ComponentActionManager(actionList);
    try{
      actionManager.setupActions(layout);
    }
    catch(IllegalArgumentException e){
      // Do nothing
    }
  }
}
