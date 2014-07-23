package com.sarality.app.view.action.test;

import android.test.ActivityUnitTestCase;

import com.dothat.app.module.reminder.ReminderListActivity;

/**
 * Base Test that should be extended by all test cases that want to use mockito.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class BaseUnitTest extends ActivityUnitTestCase<ReminderListActivity> {

  public BaseUnitTest() {
    super(ReminderListActivity.class);
  }

  protected void setUp() throws Exception {
    super.setUp();
    System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
  }
}
