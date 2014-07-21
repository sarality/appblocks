package com.sarality.app.config.test;

import android.test.ActivityUnitTestCase;

import com.dothat.app.module.reminder.ReminderListActivity;
import com.sarality.app.config.AppInitialized;

/**
 * Tests for {@link AppInitialized}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class AppInitializedTest extends ActivityUnitTestCase<ReminderListActivity> {

  public AppInitializedTest() {
    super(ReminderListActivity.class);
  }

  public void testConstructor() {
    AppInitialized initialized = new AppInitialized(getInstrumentation().getTargetContext().getApplicationContext());
    assertNotNull(initialized);
  }

  public void test_SetValue_False() {
    AppInitialized initialized = new AppInitialized(getInstrumentation().getTargetContext().getApplicationContext());
    initialized.setValue(false);
    assertSame(false, initialized.getValue());
  }

  public void testIsEditable() {
    AppInitialized initialized = new AppInitialized(getInstrumentation().getTargetContext().getApplicationContext());
    assertSame(true, initialized.isEditable());
  }

  public void testSetValue_True() {
    AppInitialized initialized = new AppInitialized(getInstrumentation().getTargetContext().getApplicationContext());
    initialized.setValue(true);
    assertSame(true, initialized.getValue());
  }

}
