package com.sarality.app.view.action.test;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityUnitTestCase;

/**
 * Base Test that should be extended by all test cases that want to use mockito.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class BaseUnitTest extends ActivityUnitTestCase<TestActivity> {
  protected Context context;

  public BaseUnitTest() {
    super(TestActivity.class);
  }

  protected void setUp() {
    try {
      super.setUp();
      System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
      context = getInstrumentation().getTargetContext().getApplicationContext();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public Context getContext(){
    return context;
  }
}

final class TestActivity extends Activity {

  public TestActivity() {
      super(); 
  }
}