package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.mockito.Mockito;

import android.content.Context;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.action.test.BaseUnitTest;
import com.sarality.app.view.datasource.DataSource;
import com.sarality.app.view.list.ListComponent;
import com.sarality.app.view.list.ListComponentLoader;

/**
 * Tests for {@link ListComponent}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ListComponentLoaderTest extends BaseUnitTest {

  public void testListComponentLoader() {
    Context context = getInstrumentation().getTargetContext().getApplicationContext();
    ListComponentLoader<String> loader = new ListComponentLoader<String>(context, null);
    assertNotNull(loader);
    assertEquals(context, loader.getContext());
  }

  @SuppressWarnings("unchecked")
  public void testLoadInBackground() {
    Context context = getInstrumentation().getTargetContext().getApplicationContext();
    List<String> list = Lists.of("Test1", "Test2", "Test3");
    DataSource<String> source = mock(DataSource.class); 
    ListComponentLoader<String> loader = new ListComponentLoader<String>(context, source);

    Mockito.when(source.getData()).thenReturn(list);
    assertEquals(list,loader.loadInBackground());
  }

  @SuppressWarnings("unchecked")
  public void testLoadData() {
    Context context = getInstrumentation().getTargetContext().getApplicationContext();
    List<String> list = Lists.of("Test1", "Test2", "Test3");
    DataSource<String> source = mock(DataSource.class); 
    ListComponentLoader<String> loader = new ListComponentLoader<String>(context, source);

    Mockito.when(source.getData()).thenReturn(list);
    assertEquals(list,loader.loadData());
  }

  @SuppressWarnings("unchecked")
  public void testLoadData_EmptyList() {
    List<String> list = Lists.of();
    DataSource<String> source = mock(DataSource.class); 
    Context context = getInstrumentation().getTargetContext().getApplicationContext();
    ListComponentLoader<String> loader = new ListComponentLoader<String>(context, source);

    Mockito.when(source.getData()).thenReturn(list);
    assertEquals(list,loader.loadData());
  }

}
