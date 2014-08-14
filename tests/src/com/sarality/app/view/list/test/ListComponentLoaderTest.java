package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.datasource.DataSource;
import com.sarality.app.view.list.ListComponent;
import com.sarality.app.view.list.ListComponentLoader;

/**
 * Tests for {@link ListComponent}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
@RunWith(RobolectricTestRunner.class)
public class ListComponentLoaderTest extends TestCase {
  Context context;

  @Before
  public void setUp() {
    context = Robolectric.application;
  }

  @Test
  public void testListComponentLoader() {
    ListComponentLoader<String> loader = new ListComponentLoader<String>(context, null);
    assertNotNull(loader);
    assertEquals(context, loader.getContext());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testLoadInBackground() {
    List<String> list = Lists.of("Test1", "Test2", "Test3");
    DataSource<String> source = mock(DataSource.class);
    ListComponentLoader<String> loader = new ListComponentLoader<String>(context, source);

    Mockito.when(source.getData()).thenReturn(list);
    assertEquals(list, loader.loadInBackground());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testLoadData() {
    List<String> list = Lists.of("Test1", "Test2", "Test3");
    DataSource<String> source = mock(DataSource.class);
    ListComponentLoader<String> loader = new ListComponentLoader<String>(context, source);

    Mockito.when(source.getData()).thenReturn(list);
    assertEquals(list, loader.loadData());
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testLoadData_EmptyList() {
    List<String> list = Lists.of();
    DataSource<String> source = mock(DataSource.class);
    ListComponentLoader<String> loader = new ListComponentLoader<String>(context, source);

    Mockito.when(source.getData()).thenReturn(list);
    assertEquals(list, loader.loadData());
  }
}
