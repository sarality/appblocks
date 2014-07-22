package com.sarality.app.view.action.test;

import android.test.ActivityUnitTestCase;

import com.dothat.app.module.reminder.ReminderListActivity;

public class AppblockActivityTest extends ActivityUnitTestCase<ReminderListActivity> {

  public AppblockActivityTest() {
    super(ReminderListActivity.class);
  }

  protected void setUp() throws Exception {
    super.setUp();
    System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
  }
}
