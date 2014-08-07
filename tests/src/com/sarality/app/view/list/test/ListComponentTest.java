package com.sarality.app.view.list.test;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.mockito.Mockito;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.widget.ListView;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.view.action.ViewAction;
import com.sarality.app.view.action.test.BaseUnitTest;
import com.sarality.app.view.datasource.DataSource;
import com.sarality.app.view.list.ListComponent;
import com.sarality.app.view.list.ListComponentLoaderCallback;

/**
 * Tests for {@link ListComponent}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class ListComponentTest extends BaseUnitTest {

  public void testGetView() {
    ListView view = new ListView(context);
    ListComponent<String> component = new ListComponent<String>(null, view, null);
    assertNotNull(component);
    assertEquals(view, component.getView());
  }

  public void testRowAction() {
    ViewAction action1 = mock(ViewAction.class);
    ViewAction action2 = mock(ViewAction.class);
    ViewAction action3 = mock(ViewAction.class);

    List<ViewAction> list = Lists.of(action1, action2, action3);
    ListComponent<String> component = new ListComponent<String>(null, null, null);
    component.registerRowAction(action1);
    component.registerRowAction(action2);
    component.registerRowAction(action3);

    assertEquals(list, component.getRowActions());
  }

  public void testAction() {
    ViewAction action1 = mock(ViewAction.class);
    ViewAction action2 = mock(ViewAction.class);
    ViewAction action3 = mock(ViewAction.class);

    List<ViewAction> list = Lists.of(action1, action2, action3);
    ListComponent<String> component = new ListComponent<String>(null, null, null);
    component.registerAction(action1);
    component.registerAction(action2);
    component.registerAction(action3);

    assertEquals(list, component.getActions());
  }

  @SuppressWarnings("unchecked")
  public void testInit() {
    FragmentActivity activity = mock(FragmentActivity.class);
    ListView view = new ListView(context);
    ListComponent<String> component = new ListComponent<String>(activity, view, null);
    DataSource<String> source = mock(DataSource.class);
    String item1 = "Item1";
    String item2 = "Item2";
    String item3 = "Item3";
    List<String> list = Lists.of(item1, item2, item3);

    Mockito.when(source.getData()).thenReturn(list);

    component.init(source);
    assertNotNull(view.getAdapter());
  }

  @SuppressWarnings("unchecked")
  public void testInitAsync() {
    FragmentActivity activity = mock(FragmentActivity.class);
    LoaderManager manager = mock(LoaderManager.class);
    Loader <List<String>> loader = mock(Loader.class);
    ListComponent<String> component = new ListComponent<String>(activity, null, null);
    Mockito.when(activity.getSupportLoaderManager()).thenReturn(manager);
    Mockito.when(manager.initLoader(Mockito.eq(0), (Bundle)Mockito.eq(null),(LoaderCallbacks<List<String>>) Mockito.any(ListComponentLoaderCallback.class))).thenReturn(loader);
  
    component.initAsync(null);
    Mockito.verify(loader).forceLoad();
  }

  public void testRender() {
    FragmentActivity activity = mock(FragmentActivity.class);
    ListView view = new ListView(context);
    ListComponent<String> component = new ListComponent<String>(activity, view, null);
    List<String> list = Lists.of();

    component.render(list);
    assertNotNull(view.getAdapter());
  }
}
