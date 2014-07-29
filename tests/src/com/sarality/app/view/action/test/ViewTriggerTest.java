package com.sarality.app.view.action.test;

import junit.framework.TestCase;

import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.action.ViewTrigger;

/**
 * Tests for {@link ViewTrigger}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ViewTriggerTest extends TestCase {

  public void testViewTrigger() {
    int viewId = 1234;
    TriggerType trigger = TriggerType.CLICK_LIST_ITEM;
    ViewTrigger viewTrigger = new ViewTrigger(viewId, trigger);

    assertNotNull(viewTrigger);
    assertEquals(trigger, viewTrigger.getTriggerType());
    assertEquals(viewId, viewTrigger.getViewId());
  }

  public void testEqualsObject() {
    int viewId = 1234;
    TriggerType trigger = TriggerType.CLICK_LIST_ITEM;
    ViewTrigger viewTrigger1 = new ViewTrigger(viewId, trigger);
    ViewTrigger viewTrigger2 = new ViewTrigger(viewId, trigger);

    assertEquals(true, viewTrigger1.equals(viewTrigger2));
  }

}
