package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.list.ListComponent;
import com.sarality.app.view.list.ListComponentLoader;
import com.sarality.app.view.list.ListComponentLoaderCallback;

/**
 * Tests for {@link ListComponentLoaderCallback}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ListComponentLoaderCallbackTest extends TestCase {

  @Test
  public void testListComponentLoaderCallback() {
    Activity activity = mock(Activity.class);
    ListComponentLoaderCallback<Integer> loaderCallback = new ListComponentLoaderCallback<Integer>(activity, null, null);
    assertNotNull(loaderCallback);
    assertEquals(ListComponentLoader.class, loaderCallback.onCreateLoader(12, null).getClass());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testOnLoadFinished() {
    Activity activity = mock(Activity.class);
    ListComponent<Integer> component = mock(ListComponent.class);
    ListComponentLoaderCallback<Integer> loaderCallback = new ListComponentLoaderCallback<Integer>(activity, null,
        component);
    List<Integer> list = Lists.of();
    loaderCallback.onLoadFinished(null, list);
    Mockito.verify(component).render(list);
  }
}
