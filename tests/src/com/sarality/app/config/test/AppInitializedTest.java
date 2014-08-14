package com.sarality.app.config.test;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import com.sarality.app.config.AppInitialized;

/**
 * Tests for {@link AppInitialized}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */

@RunWith(RobolectricTestRunner.class)
public class AppInitializedTest extends TestCase {

  @Test
  public final void testAppInitialized() {
    AppInitialized initialized = new AppInitialized(Robolectric.application);
    assertNotNull(initialized);
    initialized.setValue(false);
    assertSame(false, initialized.getValue());
    assertSame(true, initialized.isEditable());
  }

  @Test
  public void testSetValue_True() {
    AppInitialized initialized = new AppInitialized(Robolectric.application);
    initialized.setValue(true);
    assertSame(true, initialized.getValue());
  }
}
