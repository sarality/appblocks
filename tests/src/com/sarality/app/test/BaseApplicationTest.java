package com.sarality.app.test;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;

import com.sarality.app.BaseApplication;

/**
 * Tests for {@link BaseApplication}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class BaseApplicationTest extends TestCase {

  @Test
  public final void testOnCreate() {
    TestBaseApplication application = new TestBaseApplication();
    assertNotNull(application);
    application.onCreate();
  }

  @Test
  public final void testBaseApplicationContext() {
    TestBaseApplication application = new TestBaseApplication(Robolectric.application);
    assertNotNull(application.getApplicationContext());
  }

  @Test
  public final void testGetTable() {
    TestBaseApplication application = new TestBaseApplication();
    assertNull(application.getTable("Test"));
  }
}

class TestBaseApplication extends BaseApplication {
  public TestBaseApplication(Context context) {
    super(context);
  }

  public TestBaseApplication() {
    super();
  }

  @Override
  protected void initLogging() {
  }

  @Override
  protected void initModules() {
  }

  @Override
  protected void initApp() {
  }

}
